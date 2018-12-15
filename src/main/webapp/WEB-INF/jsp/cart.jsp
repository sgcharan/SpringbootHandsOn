<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">
	<h1 class="display-4">Cart <small class="text-muted">${emptyMesg}</small></h1>
		<div class="card-deck">
			<c:forEach items="${cartList}" var = "product">
				<div  class="card" style="max-width: 18rem;margin-bottom:20px;">
				 <img id="images" class="card-img-top" src="${product.imgUrl}" alt="placeholder-image"></img>					
				 <div class="card-body">
								<h4 class="card-title">${product.name}</h4>	
							 	<div class= "card-text">								
								<div>Weight: ${product.weight}</div>
								<div>Price: ${product.price}</div>
								<form:form method="post" commandName="product">
									<%-- <form:hidden path="id" value = "${product.id}"/> --%>
									<form:hidden path="name" value = "${product.name}"/>
									<form:hidden path="weight" value = "${product.weight}"/>
									<form:hidden path="price" value = "${product.price}"/>
									<form:hidden path="imgUrl" value="${product.imgUrl}"/>
									<form:hidden path="added" value = "N"/>
									<input type="submit" class="btn btn-warning" value = "Remove"></input>
								</form:form>
								</div>
				 </div>
				</div>
			</c:forEach>					
		</div>
		<a role="button" href="/products" class="btn btn-info">Shop</a>
		<a id ="checkout" role="button" href="/orderFor" class="btn btn-success">Checkout</a>
	</div>

<script>
 function verifyChekout(){
	var elem = document.getElementById("checkout");
	var isempty = "${emptyMesg}";
	console.log(isempty);
	if(isempty=="is empty"){
	elem.classList.remove("btn-success");
	elem.classList.add("btn-secondary");
	elem.classList.add("disabled");

	
/* 	image="<img class=\"card-image-top\" "+
			"src="+"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9Gotqbt4gGwMIi3y9ArY87WYIMzevpSdHSHAJxhCaGSCd1aga "+
			"></img>"; */
			
   var card = $('<div />');
   card.attr('class',"card");
   card.attr('style',"max-width: 18rem;margin-bottom:20px;");
   card.appendTo(jQuery('.card-deck'));
			
			
	var img = $('<img />');
	img.attr('class',"card-image-top");
	img.attr('height',"150px");
	img.attr('src',"https://1010experiencias.com/images/cart_empty.png");
	img.appendTo('.card');
	}
 }
 
 verifyChekout();
</script>	

<%@ include file="common/footer.jspf" %>
