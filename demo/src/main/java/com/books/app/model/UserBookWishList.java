package com.books.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_book_wish_list")
@IdClass(UserBookWishListId.class)
public class UserBookWishList {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "book")
    private String book;

    // Constructors, getters, and setters

    public UserBookWishList() {
    }

    public UserBookWishList(Long userId, String book) {
        this.userId = userId;
        this.book = book;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

}