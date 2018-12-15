<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<%-- ${users} ${productList} ${selectedOption} --%>

	<div class="container">
	<div class="d-flex align-items-center justify-content-between"><h1 class="display-4">Products</h1> 
	<a role="button" href="/addOtherUsers" class="btn btn-primary margin"><i class="fas fa-user-plus"></i> Add Customers</a>
	</div>
	<h1 class="display-4 ">Added Customers</h1> 
	<c:forEach items="${users}" var="user" varStatus="i">
		<div class="d-flex flex-column">
		<div><h4 class="display-8"><a class="btn btn-outline-primary dynamic" href="" id="custLink">${user}</a></h4></div>	
		<div class="card-deck" id="productDeck">
			<c:forEach items="${productList}" var = "product" varStatus="j">
						<div   class="card" style="max-width: 18rem; margin-bottom:20px;">
							 <img class="card-img-top" src="${product.imgUrl}" alt="placeholder-image"></img>						
							 <div class="card-body">
								<h4 class="card-title">${product.name}</h4>							 
							 	<div class= "card-text">
								<div>Weight: ${product.weight}</div>
								<div>Price: ${product.price}</div>
								<form:form id="productForm${user}${j.index}" class="productForm" method="post" commandName="product" >					
												<%-- <form:hidden path="id" value="${product.id}"/> --%>												
												<form:hidden id="name${user}${j.index}" name="name" path="name" value="${product.name}"/>
												<form:hidden id="weight${user}${j.index}" name="weight" path="weight" value="${product.weight}"/>
												<form:hidden id="price${user}${j.index}" name="price" path="price" value="${product.price}"/>
												<form:hidden id="imgUrl${user}${j.index}" name="imgUrl" path="imgUrl" value="${product.imgUrl}"/>
												<form:hidden id="added${user}${j.index}" name="added" path="added" value="Y"/>
												<form:hidden id="forusername${user}${j.index}" name="forusername" path="forusername" value="${user}"/>
<%-- 												<div class="form-group">
												<label for="productformselect">Ordering For: </label>
												<form:select id ="productformselect" class="form-control margin" path="forusername" required="required">
													<form:option value="${selectedOption}"/>
													<form:options items="${users}"/>
												</form:select>
												</div> --%>
												<input id="addcartbtn${user}${j.index}" type="submit" class="btn btn-primary addcart" value = "Add to Cart"></input>
								</form:form>
								</div>
							 </div>
						</div>
						
			<script>
				
			</script>

			</c:forEach>	
			
		<script>
		
		var dynId = "productDeck"+"${user}";
		console.log("dynId "+dynId);
		
		$("#productDeck").attr("id",dynId);
		
		var dynCustId = "custLink"+"${user}";
			var dynHref="#"+dynId;
			console.log("dynhref "+dynHref);
			console.log("dynCustId "+dynCustId);			
					$("#custLink").attr("id",dynCustId);
					$("#"+dynCustId).attr("href",dynHref);	    
		</script>	
								
		</div>
		</div>
	</c:forEach>
	<a role="button" id="cartcount" href="/cartMulti" class="btn btn-info">Cart <span class="badge badge-pill badge-dark">${cartCount}</span></a>		
	</div>
	
	<script>
	
	function userBasedDynamicToggle(){
		$(".card-deck").hide();
	
		var alldecks = $(".dynamic").get();
		console.log("alldecks "+alldecks);
	
		alldecks.map(function(elem){
			console.log("elem "+String(elem)+" typeof "+ typeof elem);
			console.log("hrefsplit "+String(elem).split("#"));
			console.log("href "+String(elem).split("#")[1]);
			var href = String(elem).split("#")[1];
			var user = href.split("productDeck");
			console.log("user "+user[1]);
			var linkId = "#custLink"+user[1];
			console.log("linkId "+linkId);
			var deckId = "#"+href;
			console.log("deckId  "+deckId);
			
				$(linkId).click(function(){							
				$(deckId).toggle(1000);
			});	
		});	
	}
	
	userBasedDynamicToggle();
	
	$(document).ready(function(){
		var formarr = $(".productForm").get();
		console.log("formarr "+formarr);
		
		formarr.map(function(elem,index){
			var elemarr=[];
			console.log("elemarr "+elemarr);
	 		for(let i=0;i<elem.length;i++){
	 			elemarr.push(elem[i]);
	 		}
	 		console.log("elemarr "+elemarr);
			//console.log("index"+index+"elem "+elem+" elem id "+elem.id+"elem value "+elem[index].value);
			var elemId = "#"+elem.id;
			
			onsubmit(elemarr);
			
			function onsubmit(elemarr){
			$(elemId).submit(function(event){
				event.preventDefault();
				console.log("elemarr sub"+elemarr);
				fire_ajax_submit(elemarr);
			});
			}
		});
	}); 
	
	function fire_ajax_submit(elemarr){
		console.log("elemarr "+elemarr.length+elemarr[6].value+" "+elemarr[6].id);
		var reqBody={};
		for(let arr=0;arr<elemarr.length-1;arr++){
			reqBody[elemarr[arr].name] = elemarr[arr].value;
			console.log("reqbody elem"+reqBody[elemarr[arr].name]);
		}
		var buttonid =  "#"+elemarr[elemarr.length-1].id;
		$(buttonid).prop("disabled","true");
		console.log("json "+JSON.stringify(reqBody));
	 	$.ajax({
				type: "POST",
		        contentType: "application/json",
		        url: "/productsMulti",
		        data: JSON.stringify(reqBody),
		        dataType: 'json',
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
					var cartcount = "Cart <span class=\"badge badge-pill badge-dark\">"+data.cartCount+"</span>";
		            console.log("SUCCESS : ", data);
		            console.log("cartcount : ", cartcount);
		            $(buttonid).prop("disabled", false);
					$("#cartcount").html(cartcount);
		        },
		        error: function (e) {
		            console.log("ERROR : ", e);
		            $(buttonid).prop("disabled", false);

		        }
		
		
	 	}); 
		
	}
	
	</script>
<%@ include file="common/footer.jspf" %>