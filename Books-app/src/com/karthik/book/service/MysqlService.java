package com.karthik.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.karthik.book.dto.User;

public class MysqlService {
	public static void main(String[] args) throws Exception {
			UserService service = new UserService();
//			System.out.println("isValiduserName: " + service.isValidUserId("ja"));
			service.getUserById("kskarthik08");
	}

}
