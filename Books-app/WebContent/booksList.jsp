<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import='com.karthik.book.dto.Book'%>
<%@ page import='java.util.List' %>
<%@ include file="search.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table style="width:100%">
  <tr align="center">
    <th>Sl No</th>
    <th>Title</th> 
    <th>Author</th>
    <th>ISBN</th>
    <th>Publication Year</th>
  </tr>
  	<% List<Book> booksList = (List<Book>) session.getAttribute("books");
		if (booksList != null) {
			for (int i = 0; i < booksList.size(); i++) {
				Book book = booksList.get(i);
				out.println("<tr align='center'>");
				out.println("<td>" + (i+1) + "</td>");
				out.println("<td><a href='book?id=" + book.getId() + "'>" + book.getTitle() + "</a></td>");
				out.println("<td>" + book.getAuthor() + "</td>");
				out.println("<td>" + book.getIsbn() + "</td>");
				out.println("<td>" + book.getPublicationYear() + "</td>");
				out.println("</tr>");
			}
		}
	%>
</table>
</body>
</html>