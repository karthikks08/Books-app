package com.karthik.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.karthik.book.dto.Book;
import com.karthik.book.dto.User;

public class BookService {
	
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
//			System.out.println("Query: " + query);
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("bookId"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setPublicationYear(resultSet.getInt("publicationYear"));
				System.out.println(book);
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
				System.out.println(book);
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
	
	public static void main(String[] args) {
		BookService service = new BookService();
		List<Book> books = service.getBooksByTitleAndAuthorAndISBN("Rose", "david", null);
		for(Book b: books) {
			System.out.println(b);
		}
	}

}
