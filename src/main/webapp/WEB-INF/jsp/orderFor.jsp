<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<div class="jumbotron d-flex align-items-center flex-column" style="margin:20px 150px 20px 150px;">
	<div><h1 class="display-4 text-center"> Order For </h1></div>
	<div class="container">
				<div class="card-deck  d-flex justify-content-center">
				
				<div class="card" >
				<a href="/products" class="btn btn-fix">
					<div class="card-body d-flex align-items-center flex-column">
					<i class="fas fa-user fa-7x"></i>
					<div class="card-text">Self</div>
					</div>
				</a>				
				</div>
				
				<div class="card" >
				<a href="/productsMulti" class="btn btn-fix">
					<div class="card-body d-flex align-items-center flex-column" >
					<div><i class="fas fa-users fa-7x"></i></div>
					<div class="card-text">Others</div>
					</div>
				</a>
				</div>				
				</div>
	</div>
</div>				
</div>

<%@ include file="common/footer.jspf" %>