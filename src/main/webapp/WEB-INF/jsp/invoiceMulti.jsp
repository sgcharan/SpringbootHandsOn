<%@ include file="common/header.jspf" %>

<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<h1 class="display-4 text-center">Invoice</h1> 
		<div class="card-deck ">
		<div  class="card mx-auto">
		<div class="card-body d-flex align-items-left flex-column">	
			<div class="d-flex flex-row align-items-center justify-content-between"><h4 class="card-title">User Based Invoice</h4>
			<h5 class="text-right">Invoice Number: ${invoiceId}</h5></div>
		<div class="card-deck ">
		<div  class="card">	
		<div class="card-body d-flex align-items-left flex-column">
			<c:forEach items="${userList}" var="user" varStatus="i">
			<div>
			<h5 class="card-text text-left margin">Customer:</h5> 
				<div class="table-responsive table-borderless"> 
					<table class="table">				
					<thead class="thead-light">
					<tr>
						<th>${user.others}</th>
					</tr>
					</thead>
					</table>
				</div>
			</div>
			<c:set var="productList">productList${user.others}</c:set>	
			<div class="d-flex flex-row justify-content-between"><h5>Items Ordered:</h5><h5 id="orderof${user.others}"></h5></div>							

				 <div class="table-responsive">
				 	<table class="table">
				 		<thead class="thead-light">
				 			<tr>
				 				<th>Product</th>
				 				<th>Weight</th>
				 				<th>Price</th>
				 			</tr>
				 		</thead>
				 		<tbody>
				 <c:forEach items="${requestScope[productList]}" var="product">				 		
				 			<tr>				 			
				 				<td>${product.name}</td>
				 				<td>${product.weight}</td>
				 				<td>${product.price}</td>
				 			</tr>
				 			<script>
				 			var ordertag = "#orderof"+"${user.others}";
				 			console.log(ordertag);
				 			var ordernumber = "OrderNumber: ${product.orderNumber}";
				 			console.log("${product.orderNumber}");
				 			$(ordertag).html(ordernumber);
				 			</script>
				 </c:forEach>				 			
				 		</tbody>				 	
				 	</table>
				 </div>
			</c:forEach>
	
		</div>	
		</div>	
		</div>
		</div>	
		</div>		
		</div>	
	</div>


<%@ include file="common/footer.jspf" %>