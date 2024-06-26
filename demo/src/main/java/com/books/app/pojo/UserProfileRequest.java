package com.books.app.pojo;

import java.util.List;

public class UserProfileRequest {

	private String firstName;

	private String lastName;

	private String username;

	private String phoneNo;

	private List<String> favGenre;

	private List<String> readingPref;

	private List<String> bookWishList;

	private List<String> bookOwnedList;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<String> getFavGenre() {
		return favGenre;
	}

	public void setFavGenre(List<String> favGenre) {
		this.favGenre = favGenre;
	}

	public List<String> getReadingPref() {
		return readingPref;
	}

	public void setReadingPref(List<String> readingPref) {
		this.readingPref = readingPref;
	}

	public List<String> getBookWishList() {
		return bookWishList;
	}

	public void setBookWishList(List<String> bookWishList) {
		this.bookWishList = bookWishList;
	}

	public List<String> getBookOwnedList() {
		return bookOwnedList;
	}

	public void setBookOwnedList(List<String> bookOwnedList) {
		this.bookOwnedList = bookOwnedList;
	}
	
}
