package com.karthik.book.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthik.book.dto.Book;
import com.karthik.book.service.BookService;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet(urlPatterns={"/search" , "/book"})
public class BookServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(BookServlet.class.getName());
	public static final BookService BOOK_SERVICE = new BookService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = new String(request.getRequestURI());
		System.out.println("uri: " + uri);
		if(uri.equals("/Books-app/search")) {
			doGetSearch(request, response);
		}else if(uri.equals("/Books-app/book")) {
			System.out.println("books");
			doGetBook(request, response);
		}
	}

	private void doGetBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		System.out.println("book id: " + id);
		Book book = BOOK_SERVICE.getBook(Integer.parseInt(id));
		session.setAttribute("book", book);
		response.sendRedirect("bookDetails.jsp");
		return;
	}

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("search post");
	}

}
