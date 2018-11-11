package com.karthik.book.dto;

public class User {
	private int id;
	private String userId;
	private String password;

	public User(int id, String userId, String password) {
		this.id = id;
		this.userId = userId;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + "]";
	}

}
