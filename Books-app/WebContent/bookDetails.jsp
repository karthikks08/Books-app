<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import='com.karthik.book.dto.Book'%>
<%@ page import="com.karthik.book.dto.Review"%>
<%@ page import="java.util.*"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book details</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-6">
				<div class="card">
					<div class="card-body">
						<div class="card-text">
							<%
								Book book = (Book) session.getAttribute("book");
								List<Review> reviews = new ArrayList<>((LinkedHashSet<Review>) session.getAttribute("reviews"));
								int userId = (int) session.getAttribute("user");
							%>
							<h3>Book details:</h3>
							<table class="table">
								<tr>
									<th scope="col-5">Title</th>
									<td scope="col-7"><%=book.getTitle()%></td>
								</tr>
								<tr>
									<th scope="col">ISBN</th>
									<td scope="col"><%=book.getIsbn()%></td>
								</tr>
								<tr>
									<th scope="col">Author</th>
									<td scope="col"><%=book.getAuthor()%></td>
								</tr>
								<tr>
									<th scope="col">Publication Year</th>
									<td scope="col"><%=book.getPublicationYear()%></td>
								</tr>
								<tr>
									<th scope="col">Rating</th>
									<td scope="col"></td>
								</tr>
							</table>


						</div>
					</div>
				</div>
			</div>
			<div class="col-6">
				<img class="card-img-top" src="book.jpg" alt="book image">
			</div>
		</div>
		<br>
		<br>
		<div class="container">
		<h5>User Reviews:</h5>
		<%
			boolean hideReviewBox = false;
			if (reviews != null) {
				Iterator<Review> itr = reviews.iterator();
				out.println("<div class='row'>");
				while (itr.hasNext()) {
					Review review = itr.next();
					out.println("<div class='col-4'>Ratings: " + review.getRating() + "<br/>");
					out.println("Comments: " + review.getComment() + "</div>");
				}
				out.println("</div>");
				if (reviews.size() == 0) {
					hideReviewBox = false;
					out.println("No reviews for the book");
				} else if (reviews.get(0).getUserId() == userId) {
					hideReviewBox = true;
				}
			}
			if (!hideReviewBox) {
		%>
		<div class="col-6">
			<div class="card">
				<div class="card-header">Your rating:</div>
				<div class="card-body">
					<div class="card-text">
						<form action="review" method="POST">
							<div class="form-group">
								<label for="rating">Rating</label> <select class="form-control"
									id=" name=" rating"" name="rating">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
							<div class="form-group">
								<label for="comment">Comment</label>
								<textarea class="form-control" id="comment" name='comment'
									rows='4' cols='50' maxlength='250'"></textarea>
							</div>
							<input type="hidden" name="bookId" value='<%=book.getId() %>'/>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>