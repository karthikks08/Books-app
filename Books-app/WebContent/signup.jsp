<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
	integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
	integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
	crossorigin="anonymous"></script>
<title>BookApp Login</title>
<style type="text/css">
#center {
	/*	width: 500px; */
	height: 400px;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
}
</style>
</head>
<body>
	<div class="bg-primary">
		<nav class=class= "navbarnavbar-default" >
			<a class="navbar-brand" href="#"> <img src="bootstrap-solid.svg"
				width="30" height="30" class="d-inline-block align-top" alt="">
				<span class="text-white">BookStore</span>
			</a>
		</nav>
	</div>
	<div class="container">

		<div id="center">
			<div class="container">
				<form action="signup" method="POST">
					<h4 class="text-center">Welcome to BookStrore</h4>
					<br>
					<div class="form-group row">
						<label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="userId"
								placeholder="email@mail.com">
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="password"
								id="inputPassword" placeholder="Password">
						</div>
					</div>
					<div class="form-group row text-center">
						<label class="col-sm-2 col-form-label">Already a user? <a
							href="login.jsp">login</a></label>
					</div>
					<div class=container-fluid">
						<div class="col-sm-12 text-center">
							<button class="btn btn-primary btn-block" title="Login">SignUp</button>
						</div>
					</div>
				</form>
				<%
					String error = (String) session.getAttribute("error");
					if (error != null) {
						out.println("<div class='alert alert-danger'><strong>Error: </strong>" + error + "</div>");
					}
					session.setAttribute("error", null);
				%>

			</div>
		</div>
	</div>
</body>
</html>