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
    @Column(name = "book_id")
    private Long bookId;

    // Constructors, getters, and setters

    public UserBookWishList() {
    }

    public UserBookWishList(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}