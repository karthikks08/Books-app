<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search for books</title>
</head>
<body>
	search books by title, isbn or author
	<form action="search" method="GET">
		TITLE: <input type="text" name="title" /> AUTHOR: <input type="text"
			name="author" /> ISBN: <input type="text" name="isbn" />
		<button>Submit</button>
	</form>
	<br /> Id:
	<%=session.getAttribute("user")%>
	<br />
</body>
</html>