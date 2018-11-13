package com.karthik.book.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Review {
	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", dateTime=" + dateTime + ", comment=" + comment
				+ ", bookId=" + bookId + ", userId=" + userId + "]";
	}
	private int id;
	private int rating;
	private Date dateTime;
	private String comment;
	private int bookId;
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getDateTime() {
		return dateTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + id;
		result = prime * result + rating;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (bookId != other.bookId)
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (id != other.id)
			return false;
		if (rating != other.rating)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	public void setDateTime(Date date) {
		this.dateTime = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
