<%@ include file="common/header.jspf" %>

	<div class="container ">
	<h1 class="display-4 text-center">Login</h1> 
		<div class="card-deck">
		<div  class="card mx-auto" style="max-width: 18rem; margin-bottom:20px;">
			<div class="card-body d-flex align-items-center flex-column">		
					<h4 class="card-title">${message}</h4>	
					    <form method="POST"  action="/login" class="form-signin">
					        <!-- <h2 class="form-heading">Log in</h2> -->
							
					        <div class="form-group ${error != null ? 'has-error' : ''} form-row">
					            
					            <div class="mx-auto margin"><input name="username" type="text" class="form-control" placeholder="Username" required/></div>
					            <div  class="mx-auto margin"><input name="password" type="password" class="form-control" placeholder="Password" required/></div>
					            
					
					            <button class="btn btn-primary btn-sm mx-auto" type="submit">Log In</button>
					        </div>
					
					    </form>	
					    <div class= "card-text">
					    <span>${msg}</span>
					    <span>${errorMsg}</span>
					    </div>
		    </div>
			<div class="card-body">
			<a href="/register">Register</a>	
			</div>			    
		</div>		
		</div>
	</div>
	
<%@ include file="common/footer.jspf" %>	
