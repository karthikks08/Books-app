<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search for books</title>
</head>
<body>
	<br>
	<div class="container">
		<form action="search" method="GET">
			<h3> Search for book</h3>
			<div class="form-row">
				<div class="col">
					<input type="text" class="form-control" name="title" placeholder="Title">
				</div>
				<div class="col">
					<input type="text" class="form-control" name="author" placeholder="Author">
				</div>
				<div class="col">
					<input type="text" class="form-control" name="isbn" placeholder="ISBN">
				</div>
			</div>
			<br>
			<div class="col">
				<button type="submit" class="form-control btn btn-primary mb-2">Submit</button>
			</div>
		</form>
	</div>
	
</body>
</html>