<%@ include file="common/header.jspf" %>

	<div class="container">
		<h1 class="display-4 text-center">Register</h1> 
		<div class="card-deck ">
		<div  class="card mx-auto" style="max-width: 18rem; margin-bottom:20px;">
		<div class="card-body d-flex align-items-center flex-column">	
		<h4 class="card-title">User Registration</h4>	
		    <form:form method="POST" class="form-signin" commandName="credential">
		        
				
		            <form:input path="username" name="username" type="text" class="form-control margin" placeholder="Username" required="required"/>
		            <form:input path="password" name="password" type="password" class="form-control margin" placeholder="Password" required="required"/>
		
		            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>	
		    </form:form>
		</div>	
		</div>		
		</div>	
	</div>


<%@ include file="common/footer.jspf" %>