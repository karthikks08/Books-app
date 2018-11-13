<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import='com.karthik.book.dto.Book'%>
<%@ page import='java.util.List'%>
<%@ include file="search.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Books</title>
</head>
<body>
	<%
		List<Book> booksList = (List<Book>) session.getAttribute("books");
		if (booksList.size() == 0) {
			out.println("<div class='alert alert-danger'><strong>Error: </strong>No matches found.</div>");
		}else{
	%>
	<br>
	<div class="container">
		<table class="table table-fixed">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Title</th>
					<th scope="col">Author</th>
					<th scope="col">ISBN</th>
					<th scope="col">Publication Year</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < booksList.size(); i++) {
							Book book = booksList.get(i);
							out.println("<tr>");
							out.println("<td scope='row'>" + (i + 1) + "</td>");
							out.println("<td><a href='book?id=" + book.getId() + "'>" + book.getTitle() + "</a></td>");
							out.println("<td>" + book.getAuthor() + "</td>");
							out.println("<td>" + book.getIsbn() + "</td>");
							out.println("<td>" + book.getPublicationYear() + "</td>");
							out.println("</tr>");
						}
				%>
			</tbody>
		</table>
	</div>
	<%	
	}
	%>
</body>
</html>