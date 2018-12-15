package com.sampleProject.controllers;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sampleProject.entity.Credentials;
import com.sampleProject.entity.Orders;
import com.sampleProject.entity.Products;
import com.sampleProject.entity.ProductsMultiAjaxResp;
import com.sampleProject.entity.Userlist;
import com.sampleProject.service.OrdersService;
import com.sampleProject.service.ProductsService;
import com.sampleProject.service.UserlistService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class WebAppController {
	
	@Autowired
	ProductsService	productService;
	
	@Autowired
	Products product;

	@Autowired
	Userlist userlist;
	
	@Autowired
	UserlistService userlistservice;	
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;	
	
	@Autowired
	BCryptPasswordEncoder bcrytpasswordencoder;
	
	@Autowired
	OrdersService ordersservice;
	
	@RequestMapping(value={"/","/welcome"}, method = RequestMethod.GET)
	public String WelcomePage() {
		
		return "orderFor";
	}

	@RequestMapping(value={"/productsMulti"}, method = RequestMethod.GET)
	public String othersProducts(ModelMap model) {
		List<String> users = new ArrayList<String>();
		model.addAttribute("product",new Products());
		List<Userlist> userlist = userlistservice.fetchOtherUsers(getLoggedInUsername());
		Iterator<Userlist> itr = userlist.iterator(); 
		
		int i= 0;
		while(itr.hasNext()){
			users.add(itr.next().getOthers());	
			
			i++;
		}
		
		
		model.addAttribute("users",users);
		model.addAttribute("productList",productService.retrieveProducts());
		model.addAttribute("cartCount", productService.getCountOfCart(getLoggedInUsername()));		
		return "productsMulti";
	}
	
	@RequestMapping(value={"/productsMulti"}, method = RequestMethod.POST)
	public ResponseEntity<ProductsMultiAjaxResp> ProductsMultiLoad(ModelMap model,@RequestBody Products product) {
		
		product.setUsername(getLoggedInUsername());
		System.out.println("post method product name "+product.getId()+" "+product.getName()+" "+product.getPrice()+" "+product.getWeight());
		productService.updateCart(product);
		//model.addAttribute("selectedOption",product.getForusername());
		//model.addAttribute("cartCount", productService.getCountOfCart(getLoggedInUsername()));
		//return "redirect:/productsMulti";
		
		ProductsMultiAjaxResp productMultiAjaxResp = new ProductsMultiAjaxResp(); 
		
		productMultiAjaxResp.setCartCount(productService.getCountOfCart(getLoggedInUsername()));
		return ResponseEntity.ok(productMultiAjaxResp);
		
		
	}	

	@RequestMapping(value={"/addOtherUsers"}, method = RequestMethod.GET)
	public String otherUsers(ModelMap model) {
		model.addAttribute("userlist",new Userlist());
		model.addAttribute("users", userlistservice.fetchOtherUsers(getLoggedInUsername()));
		return "addOtherUsers";
	}

	@RequestMapping(value={"/addOtherUsers"}, method = RequestMethod.POST)
	public String otherUsersPost(ModelMap model,@Valid Userlist userlist, BindingResult result) {
		if(result.hasErrors()){
			return "addOtherUsers";
		}
		userlist.setUsername(getLoggedInUsername());
		try{
		userlistservice.addUsers(userlist);
		}
		catch(DataIntegrityViolationException ex){
			model.addAttribute("error","User' Name and Mail id must be Unique");
		}
		model.addAttribute("users", userlistservice.fetchOtherUsers(getLoggedInUsername()));
		
		return "addOtherUsers";
	}	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView RegisterPage() {
		//model.addAttribute("credential",new Credentials());
		return new ModelAndView("register","credential",new Credentials());
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String RegisterAndAuthenticate(ModelMap model,Credentials credential) {
		String message =null;
		// authorities to be granted
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		User user = new User(credential.getUsername(), bcrytpasswordencoder.encode(credential.getPassword()), authorities);
		if(! jdbcUserDetailsManager.userExists(credential.getUsername())){
			jdbcUserDetailsManager.createUser(user);
			message = "Your account has been created!";
			
		}
		else		
			message = "You are a registered User. Try Login";		
		
		model.addAttribute("message", message);
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, String error, String logout) {
		
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
		
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutUser(HttpServletRequest request,
			HttpServletResponse response){
		
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response,
					authentication);
		}
		
		return "redirect:/";
	}

	@RequestMapping(value="/products", method = RequestMethod.GET)
	public String getProductDetails(ModelMap model) {
/*		List<String> productList = new ArrayList();
		productList.add("Milk");
		productList.add("Bread");
		productList.add("Butter");
		productList.add("Honey");*/
		//System.out.println("retrieve products"+productService.retrieveProducts());
		model.addAttribute("product",new Products());
		model.addAttribute("productList",productService.retrieveProducts());
		model.addAttribute("cartCount", productService.getCountOfCart(getLoggedInUsername()));

		return "products";
	}
	
	@RequestMapping(value="/products", method = RequestMethod.POST)
	public String addProductToCart(ModelMap model, Products product) {
		//System.out.println("in post cart");
		product.setUsername(getLoggedInUsername());
		System.out.println("post method product name "+product.getId()+" "+product.getName()+" "+product.getPrice()+" "+product.getWeight());
		productService.updateCart(product);
		
		//System.out.println("products in cart "+productService.retrieveProductsFromCart(getLoggedInUsername()));
		//System.out.println("count of cart "+productService.getCountOfCart());
		return "redirect:/products";
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public String getProductsInCart(ModelMap model) {
		//System.out.println("in post cart");
		model.addAttribute("product",new Products());
		model.addAttribute("cartList",productService.retrieveProductsFromCart(getLoggedInUsername()));
		if(productService.getCountOfCart(getLoggedInUsername()) < 1)
			model.addAttribute("emptyMesg","is empty");
		return "cart";
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.POST)
	public String deleteProductsInCart(ModelMap model, Products product) {
		
		//model.addAttribute("cartList",productService.retrieveProductsFromCart());
		product.setUsername(getLoggedInUsername());
		productService.deleteFromCart(product);
		//System.out.println("cart cnt"+productService.getCountOfCart(getLoggedInUsername()));
		return "redirect:/cart";
	}

	@RequestMapping(value="/cartMulti", method = RequestMethod.GET)
	public String getProductsInCartMulti(ModelMap model) {
		//System.out.println("in post cart");
		model.addAttribute("product",new Products());
		List<String> forUsers = productService.retrieveDistinctForUser(getLoggedInUsername());
		
		HashMap<String, List<Products>> usersMap = new HashMap<String, List<Products>>();
		
		for(int i=0;i<forUsers.size();i++){
			usersMap.put(forUsers.get(i), productService.retrieveProductsFromCartMulti(forUsers.get(i)));
		}
		
		
		model.addAttribute("cartListMap",usersMap);
		if(productService.getCountOfCart(getLoggedInUsername()) < 1)
			model.addAttribute("emptyMesg","is empty");
		return "cartMulti";
	}
	
	@RequestMapping(value="/cartMulti", method = RequestMethod.POST)
	public String deleteProductsInCartMulti(ModelMap model, Products product) {
		
		//model.addAttribute("cartList",productService.retrieveProductsFromCart());
		product.setUsername(getLoggedInUsername());
		productService.deleteFromCartMulti(product);
		//System.out.println("cart cnt"+productService.getCountOfCart(getLoggedInUsername()));
		return "redirect:/cartMulti";
	}
	@RequestMapping(value="/orderFor", method = RequestMethod.GET)
	public String orderForPage(ModelMap model) {

		return "orderFor";
	}	

		
	@RequestMapping(value="/invoiceMulti", method = RequestMethod.GET)
	public String getInvoiceMulti(ModelMap model) {
		
		
		//for each logged in user generate one invoice number
		SecureRandom random = new SecureRandom();
		String invoiceid = "INV"+random.nextInt(1000)+1;
		
		List<Products> products = null;
		Products product = null;
		
						
		List<Userlist> userlist = userlistservice.fetchOtherUsers(getLoggedInUsername());
		model.addAttribute("userList",userlist);
		for(int i=0; i<userlist.size();i++){
			Orders orders = new Orders();

			orders.setInvoiceid(invoiceid);
			System.out.println("inv "+invoiceid);
					
			
			//for each added user generate distinct order number				
			String ordernumber ="ORD"+random.nextInt(1000)+1;
			orders.setOrdernumber(ordernumber);
			System.out.println("order "+ordernumber);			
			
			//for each product orders for a user make note of user's invoice number
			products = productService.retrieveProductsFromCartMulti(userlist.get(i).getOthers());
			Iterator<Products> itr = products.iterator();
			while(itr.hasNext()){								
				product = itr.next();
				product.setOrderNumber(ordernumber);
				productService.updateCart(product);
			}
			ordersservice.saveOrderWithInvoice(orders);			
			model.addAttribute("invoiceId", invoiceid);
			model.addAttribute("productList"+userlist.get(i).getOthers(),products);
		}
		
		
		return "invoiceMulti";
	}
	public String getLoggedInUsername(){
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();		
	}
}
