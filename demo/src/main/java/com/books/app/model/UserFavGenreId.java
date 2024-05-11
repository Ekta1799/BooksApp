package com.books.app.model;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class UserFavGenreId implements Serializable {

    private Long userId;
    private String genre;

    // Constructors, equals, and hashCode methods

    public UserFavGenreId() {
    }

    public UserFavGenreId(Long userId, String genre) {
        this.userId = userId;
        this.genre = genre;
    }

	@Override
	public int hashCode() {
		return Objects.hash(genre, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFavGenreId other = (UserFavGenreId) obj;
		return Objects.equals(genre, other.genre) && Objects.equals(userId, other.userId);
	}
    
}
