package com.karthik.book.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DumpBooks {
	public void loadBooks() {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement prepStatement =null;
		ResultSet resultSet =null;
		FileReader fRead = null;
		BufferedReader in = null;
		try {
			fRead = new FileReader("/home/karthik/Desktop/Book_ServletJspProject/book_workspace/Books-app/src/com/karthik/book/database/data_files/books.txt");
			in = new BufferedReader(fRead);
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/booksApp", "booksAdmin", "booksAdmin@123");
			prepStatement = connect.prepareStatement("insert into books(isbn, title, author, publicationYear) values(?, ?, ?, ?)");
			String line = null;
			while((line = in.readLine()) != null) {
				String[] parts = line.split(",");
				if(parts.length != 4) {
					System.out.println(line);
					continue;
				}
				prepStatement.setString(1, parts[0]);
				prepStatement.setString(2, parts[1]);
				prepStatement.setString(3, parts[2]);
				prepStatement.setInt(4, Integer.parseInt(parts[3]));
				prepStatement.executeUpdate();
			}		
			System.out.println("complete");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	public static void main(String[] args) {
		DumpBooks dump = new DumpBooks();
		dump.loadBooks();		
	}
}
