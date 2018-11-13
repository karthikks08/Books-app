package com.karthik.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthik.book.dto.User;
import com.karthik.book.service.UserService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * 
 * AuthServlet handles user login, logout & signup.
 * @author karthik
 *
 */
@WebServlet(urlPatterns= {"/loginpage","/login", "/logout", "/signup"})
public class AuthServlet extends HttpServlet {
	private static final UserService USER_SERVICE =  new UserService();
	private static final Logger LOGGER = Logger.getLogger(AuthServlet.class.getName());
	
	/**
	 *  called when a GET request are made by users.
	 *  Handles user requests by calling appropriate methods.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = new String(req.getRequestURI());
		if(uri.equals("/Books-app/logout")) {
			logout(req, resp);
		}else if(uri.equals("/Books-app/loginpage")) {
			req.getSession().setAttribute("error" , "Login first");
			resp.sendRedirect("login.jsp");
		}
	}
	
	/**
	 *  called when a POST request are made by users.
	 *  Handles user requests by calling appropriate methods.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = new String(request.getRequestURI());
		System.out.println("uri: " + uri);
		if(uri.equals("/Books-app/login")) {
			login(request, response);
		} else if(uri.equals("/Books-app/signup")) {
			request.getSession().setAttribute("error" , "Login first");
			signUp(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		if(USER_SERVICE.isValidUser(userId, password)) {
			// valid user, redirect to home page(search.jsp)
			User user = USER_SERVICE.getUserById(userId);
			session.setAttribute("user", user.getId());
			LOGGER.info("Login succees: [user: " + userId + " ]" );
			response.sendRedirect("search.jsp");
			return;
		}else {
			// invalid user credentials
			session.setAttribute("error", "invalid credentials");
			response.sendRedirect("login.jsp");
			return;
		}
	}
	public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		try {
			// try registering the new user.
			USER_SERVICE.signUpUser(userId, password);
			session.setAttribute("error", null);
			response.sendRedirect("login.jsp");
			return;
		}catch(IllegalArgumentException e) {
			// store error messages in session object  
			session.setAttribute("error", e.getMessage());
			LOGGER.info(e.getMessage());
		} catch(MySQLIntegrityConstraintViolationException e) {
			session.setAttribute("error", "userId is unavailable");
			LOGGER.info("UserID unavailable; use different userId"); 
		}catch (ClassNotFoundException | SQLException e) {
			session.setAttribute("error", e.getMessage());
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}finally {
			// redirect to sign up page, if any exception occurred
			response.sendRedirect("signup.jsp");
		}
	}
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		// remove userId from session object.
		session.setAttribute("user", null);
		resp.sendRedirect("login.jsp");
		return;
	}
	
}
