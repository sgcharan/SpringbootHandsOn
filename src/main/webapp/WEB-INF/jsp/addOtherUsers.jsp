<%@ include file="common/header.jspf" %>

	<div class="container">
		<h1 class="display-4 text-center">Add Other Customers</h1> 
		<div class="card-deck ">
		<div  class="card mx-auto" style="max-width: 18rem; margin-bottom:20px;">
		<div class="card-body d-flex align-items-center flex-column">	
		<h4 class="card-title">Customer Info</h4>	
		<h6 class="text-danger">${error}</h6>	
		    <form:form method="POST" class="form-signin" commandName="userlist">
		        
				
		            <form:input path="others" name="Username" type="text" class="form-control margin" placeholder="Username" required="required"/>		            
		           <%--  <form:input path="othersaddress" name="Address" type="text" class="form-control margin" placeholder="Address" required="required"/> --%>
					<form:textarea path="othersaddress" rows="5" cols="30" class="form-control margin" placeholder="Address" required="required"/>
					<form:input path="othersemail" type="email" class="form-control margin" placeholder="Email" required="required"/>
					<form:input path="otherscontact" name="contact" type="text" class="form-control margin" placeholder="Contact Number" required="required"/>
					<form:errors path="otherscontact" cssClass="text-danger"/>
		            <button class="btn btn-sm btn-primary btn-block" type="submit">Add</button>	
		    </form:form>
		    <a role="button" href="/productsMulti" class="btn btn-sm btn-success">Done</a>
		</div>	
		</div>		
		</div>	
		<div class="d-flex flex-column">
		<h1 class="display-4 ">Added Customers</h1> 
		<p class="h8"><strong>Customer Info</strong></p>
		
				<div class="card-deck d-flex align-items-center">
			<c:forEach var="list" items="${users}">				
				<div class="card w-25 h-25" >
<!-- 				<a href="/products" class="btn btn-fix"> -->
					<div class="card-body">
					<!-- <i class="fas fa-user fa-7x"></i> -->
					<div class="card-text"><strong>Name:</strong> ${list.others}</div>
					<div class="card-text"><strong>Address:</strong> ${list.othersaddress}</div>
					<div class="card-text"><strong>Email:</strong> ${list.othersemail}</div>
					<div class="card-text"><strong>Contact:</strong> ${list.otherscontact}</div>
					</div>
<!-- 				</a> -->				
				</div>	
			
<%-- 				<ul class="list-group d-flex flex-column">
					<li class="list-group-item">
						<strong>Name:</strong> ${list.others} <strong>Address:</strong> <span>${list.othersaddress}</span>
					</li>					
				</ul> --%>
			</c:forEach>					
				</div>
				
		</div>
	</div>


<%@ include file="common/footer.jspf" %>