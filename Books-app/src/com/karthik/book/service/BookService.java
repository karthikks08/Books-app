package com.karthik.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.karthik.book.controller.BookServlet;
import com.karthik.book.dto.Book;
import com.karthik.book.dto.Review;
import com.karthik.book.dto.User;

public class BookService {
	private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());

	public List<Book> getBooksByTitleAndAuthorAndISBN(String title, String author, String isbn) {
		List<Book> booksList = new ArrayList<>();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
			statement = connect.createStatement();
			author = (author == null) ? "'%%'" : ("'%" + author + "%'");
			title = (title == null) ? "'%%'" : ("'%" + title + "%'");
			isbn = (isbn == null) ? "'%%'" : ("'%" + isbn + "%'");
			String query = "select * from books where title like "
					+ title + " and author like " + author + " and isbn like " + isbn;
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPublicationYear(resultSet.getInt("publicationYear"));
				booksList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
		return booksList;
	}

	public Book getBook(int id) {
		Book book = new Book();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
			statement = connect.createStatement();
			String query = "select * from books where bookId=" + id;

			//			System.out.println("Query: " + query);
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPublicationYear(resultSet.getInt("publicationYear"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
		return book;
	}

	public Set<Review> getReviews(int bookId, int userId){
		Set<Review> reviews = new LinkedHashSet<>();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
			statement = connect.createStatement();
			String query = "select * from reviews where bookId=" + bookId + " and userId=" + userId;
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Review review = new Review();
				review.setId(resultSet.getInt("reviewId"));
				review.setRating(resultSet.getInt("rating"));
				review.setComment(resultSet.getString("comments"));
				review.setBookId(resultSet.getInt("bookId"));
				review.setUserId(resultSet.getInt("userId"));
				review.setDateTime(resultSet.getDate("dateTime"));
				reviews.add(review);
			}
			query = "select * from reviews where bookId=" + bookId;
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Review review = new Review();
				review.setId(resultSet.getInt("reviewId"));
				review.setRating(resultSet.getInt("rating"));
				review.setComment(resultSet.getString("comments"));
				review.setBookId(resultSet.getInt("bookId"));
				review.setUserId(resultSet.getInt("userId"));
				review.setDateTime(resultSet.getDate("dateTime"));
				reviews.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
		return reviews;
	}

	public void submitReview(Review review) {
		Connection connect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
			String query = "insert into reviews(rating, dateTime, comments, bookId, userId) values("
					+ review.getRating() + ", "
					+ "'" + review.getDateTime() + "', "
					+ "'" + review.getComment() + "', "
				    + review.getBookId() + ", "
					+ review.getUserId() + ")";
			LOGGER.info("Query: " + query);
			statement = connect.prepareStatement(query);
			LOGGER.info("submitting comment: " + (statement.executeUpdate(query)==1 ? "success": "failure"));			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
	}

}
