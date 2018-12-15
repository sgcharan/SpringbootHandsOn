<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
	<div class="d-flex align-items-center justify-content-between"><h1 class="display-4">Products</h1> 
	<a role="button" href="/addOtherUsers" class="btn btn-primary margin"><i class="fas fa-user-plus"></i> Add Customers</a>
	</div>
		<div class="card-deck">
			<c:forEach items="${productList}" var = "product">
						<div  class="card" style="max-width: 18rem; margin-bottom:20px;">
							 <img class="card-img-top" src="${product.imgUrl}" alt="placeholder-image"></img>						
							 <div class="card-body">
								<h4 class="card-title">${product.name}</h4>							 
							 	<div class= "card-text">
								<div>Weight: ${product.weight}</div>
								<div>Price: ${product.price}</div>
								<form:form id="productForm" method="post" commandName="product" >					
												<%-- <form:hidden path="id" value="${product.id}"/> --%>												
												<form:hidden path="name" value="${product.name}"/>
												<form:hidden path="weight" value="${product.weight}"/>
												<form:hidden path="price" value="${product.price}"/>
												<form:hidden path="imgUrl" value="${product.imgUrl}"/>
												<form:hidden path="added" value="Y"/>
												<div class="form-group">
												<label for="productformselect">Ordering For: </label>
												<form:select id ="productformselect" class="form-control margin" path="forusername" required="required">
													<form:option value="${selectedOption}"/>
													<form:options items="${users}"/>
												</form:select>
												</div>
												<input type="submit" class="btn btn-primary" value = "Add to Cart"></input>
								</form:form>
								</div>
							 </div>
						</div>

			</c:forEach>					
		</div>
	<a role="button" href="/cartMulti" class="btn btn-info">Cart <span class="badge badge-pill badge-dark">${cartCount}</span></a>		
	</div>
<%@ include file="common/footer.jspf" %>