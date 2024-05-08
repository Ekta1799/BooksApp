package com.books.app.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserFavGenreId implements Serializable {

    private Long userId;
    private Long genreId;

    // Constructors, equals, and hashCode methods

    public UserFavGenreId() {
    }

    public UserFavGenreId(Long userId, Long genreId) {
        this.userId = userId;
        this.genreId = genreId;
    }

    // Implement equals and hashCode based on userId and genreId

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFavGenreId that = (UserFavGenreId) o;

        if (!userId.equals(that.userId)) return false;
        return genreId.equals(that.genreId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + genreId.hashCode();
        return result;
    }
    
}
