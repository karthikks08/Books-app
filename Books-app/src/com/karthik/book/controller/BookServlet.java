package com.karthik.book.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthik.book.dto.Book;
import com.karthik.book.dto.Review;
import com.karthik.book.service.BookService;

/**
 * BookServlet handles book search, review requests from user.
 */
@WebServlet(urlPatterns={"/search" , "/book", "/review"})
public class BookServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(BookServlet.class.getName());
	public static final BookService BOOK_SERVICE = new BookService();

	/**
	 *  called when a GET request are made by users.
	 *  Handles user requests by calling appropriate methods.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("user");
		if(userId == null) {
			// to redirect any unauthenticated user(Users who have not logged in) requests.
			response.sendRedirect("loginpage");
		}else {
			// logged in user requests are handled based on request URI.
			String uri = new String(request.getRequestURI());
			if(uri.equals("/Books-app/search")) {
				doGetSearch(request, response);
			}else if(uri.equals("/Books-app/book")) {
				doGetBook(request, response);
			}
		}
	}

	/**
	 *  called when a POST request are made by users.
	 *  Handles user requests by calling appropriate methods.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("user");
		if(userId == null) {
			// to redirect any unauthenticated user(Users who have not logged in) requests.
			response.sendRedirect("loginpage");
			return;
		}else {
			// logged in user requests are handled based on request URI.
			String uri = new String(request.getRequestURI());
			if(uri.equals("/Books-app/review")) {
				postReview(request, response);

			}
		}

	}

	/**
	 * gets required book from BookService based on bookId & redirect to bookDetails page.
	 */
	private void doGetBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user");
		String bookId = request.getParameter("id");
		Book book = BOOK_SERVICE.getBook(Integer.parseInt(bookId));
		Set<Review> reviews = BOOK_SERVICE.getReviews(Integer.parseInt(bookId), userId);
		session.setAttribute("book", book);
		session.setAttribute("reviews", reviews);
		response.sendRedirect("bookDetails.jsp");
		return;
	}

	/*
	 * Gets all matched books, and shows them on the booksList page.
	 */
	public void doGetSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("search get");
		String title = request.getParameter("title");
		String isbn = request.getParameter("isbn");
		String author = request.getParameter("author");
		LOGGER.info("searching for book: ["
				+ "title: " + title + "; "
				+ "isbn: " + isbn + "; "
				+ "author: " + author + "]");
		List<Book> booksList = BOOK_SERVICE.getBooksByTitleAndAuthorAndISBN(title, author, isbn);
		HttpSession session = request.getSession();
		session.setAttribute("books", booksList);
		response.sendRedirect("booksList.jsp");
		return;
	}



	/*
	 * handles review submission by user
	 */
	public void postReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rating = request.getParameter("rating");
		String comment = request.getParameter("comment");
		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user");
		Review review = new Review();
		review.setUserId(userId);
		review.setBookId(Integer.parseInt(bookId));
		review.setRating(Integer.parseInt(rating));
		review.setComment(comment);
		review.setDateTime(Date.valueOf(LocalDate.now()));
		BOOK_SERVICE.submitReview(review);
		response.sendRedirect("book?id=" + bookId);
	}

}
