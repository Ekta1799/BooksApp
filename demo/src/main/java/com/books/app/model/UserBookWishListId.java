package com.books.app.model;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class UserBookWishListId implements Serializable {

	private Long userId;
    private String book;

    // Constructors, equals, and hashCode methods

    public UserBookWishListId() {
    }

    public UserBookWishListId(Long userId, String book) {
        this.userId = userId;
        this.book = book;
    }

	@Override
	public int hashCode() {
		return Objects.hash(book, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBookWishListId other = (UserBookWishListId) obj;
		return Objects.equals(book, other.book) && Objects.equals(userId, other.userId);
	}

    
}
