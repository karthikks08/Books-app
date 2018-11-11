package com.karthik.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.karthik.book.dto.User;

public class UserService {
	
	public boolean isValidUser(String userId, String password) {
		System.out.println("entry");
		User user = getUserById(userId);
		System.out.println("end");

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
	
	public static void main(String[] args) {
		UserService service = new UserService();
		service.getUserById("kskarthik08");
	}
	
}
