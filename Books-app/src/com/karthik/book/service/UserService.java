package com.karthik.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.karthik.book.dto.User;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class UserService {

	public boolean isValidUser(String userId, String password) {
		User user = getUserById(userId);
		if(user != null) {
			return user.getPassword().equals(password);
		}
		return false;
	}

	public User getUserById(String user_id) {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from USER where userid='" + user_id + "'");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String userId = resultSet.getString("userId");
				String password = resultSet.getString("password");
				user = new User(id, userId, password);
			}
			System.out.println("Bye");
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

			}		}
		return user;
	}

	public boolean isValidUserId(String userId) {
		if((userId.length() >=3) && (userId.length() <=15)) {
			return true;
		}
		return false;
	}

	public boolean isValidPassword(String password) {
		if(password.length() >= 4 && password.length() <= 15) {
			return true;
		}
		return false;
	}

	public void signUpUser(String userId, String password) throws ClassNotFoundException, SQLException {
		if(isValidUserId(userId) && isValidPassword(password)) {
			boolean storedUser = storeUser(userId, password);
			if(storedUser) {
				throw new IllegalArgumentException("User already exists");
			}
			return;
		} else {
			throw new IllegalArgumentException("SignUp failed: userId and password must be atleast 3 charecters long");
		}
	}

	/**
	 * stores registered new user credentials into database.
	 */
	private boolean storeUser(String userId, String password) throws ClassNotFoundException, SQLException {
		System.out.println("storeUser()");
		Connection connect = null;
		PreparedStatement statement = null;
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
		String query = "insert into USER(userId, password) values('"+ userId + "' , '"+ password + "')";
		System.out.println(query);
		statement = connect.prepareStatement(query);
		return (statement.executeUpdate() == 1) ? true: false;
	}

}
