package com.sampleProject.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampleProject.entity.Products;
import com.sampleProject.repository.ProductJPARepository;

@Service
public class ProductsService {

	@Autowired
	ProductJPARepository productJpaRepo;
	
	public List<Products> retrieveProducts() {
		return productJpaRepo.findByUsername("All");
	}
	
	public void deleteFromCart(Products product) {
		
		List<Products> products = retrieveProductsFromCart(product.getUsername());
		
		Iterator<Products> itr = products.iterator();
		int i=0;
		while(itr.hasNext()){
			
			if(product.getUsername().equals(products.get(i).getUsername()) &&
			   product.getName().equals(products.get(i).getName()))
			{
				product.setId(products.get(i).getId());
				break;
			}						
			i++;
		}
		
		if (product.getId() > 0){
			productJpaRepo.delete(product);	
		}
		
		
		//productJpaRepo.deleteByUsername(product.getUsername());
		
	}
	
	
	public void deleteFromCartMulti(Products product) {
		
		List<Products> products = retrieveProductsFromCart(product.getUsername());
		
		Iterator<Products> itr = products.iterator();
		int i=0;
		while(itr.hasNext()){
			
			if(product.getUsername().equals(products.get(i).getUsername()) &&
			   product.getName().equals(products.get(i).getName()) &&
			   product.getForusername().equals(products.get(i).getForusername()))
			{
				product.setId(products.get(i).getId());
				break;
			}						
			i++;
		}
		
		if (product.getId() > 0){
			productJpaRepo.delete(product);	
		}
		
		
		//productJpaRepo.deleteByUsername(product.getUsername());
		
	}	
	
	public void updateCart(Products product) {
		productJpaRepo.save(product);
		
		
	}
	
	public List<Products> retrieveProductsFromCart(String username){
		return productJpaRepo.findByUsername(username);
	}

	public List<Products> retrieveProductsFromCartMulti(String username){
		return productJpaRepo.findByForusername(username);
	}	
	
	public List<String> retrieveDistinctForUser(String username){
		return productJpaRepo.findDistinctLinkUser(username);
	}	
	
	public List<String> retrieveInvoiceList(String username){
		return productJpaRepo.findProductsForInvoice(username);
	}	
	
	public int getCountOfCart(String username) {
		
		return productJpaRepo.countByUsername(username);
		
	}
		
}
