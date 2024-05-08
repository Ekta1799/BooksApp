package com.books.app.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBookWishListId implements Serializable {

	private Long userId;
    private Long bookId;

    // Constructors, equals, and hashCode methods

    public UserBookWishListId() {
    }

    public UserBookWishListId(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    // Implement equals and hashCode based on userId and genreId

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBookWishListId that = (UserBookWishListId) o;

        if (!userId.equals(that.userId)) return false;
        return bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + bookId.hashCode();
        return result;
    }
}