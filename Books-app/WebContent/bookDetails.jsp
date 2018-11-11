<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import='com.karthik.book.dto.Book'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book details</title>
</head>
<body>
	out.println("details page");
	<%=session.getAttribute("book") %>
</body>
</html>