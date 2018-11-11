package com.karthik.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthik.book.dto.User;
import com.karthik.book.service.UserService;


@WebServlet("/login")
public class AuthServlet extends HttpServlet {
	private static final UserService USER_SERVICE =  new UserService();
	private static final Logger LOGGER = Logger.getLogger(AuthServlet.class.getName());
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		if(USER_SERVICE.isValidUser(userId, password)) {
			User user = USER_SERVICE.getUserById(userId);
			session.setAttribute("user", user.getId());
			LOGGER.info("Login succees: [user: " + userId + " ]" );
			response.sendRedirect("search.jsp");
			return;
		}else {
			out.println("invalid credentials");
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
