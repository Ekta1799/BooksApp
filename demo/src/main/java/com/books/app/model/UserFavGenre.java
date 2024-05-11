package com.books.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_fav_genre")
@IdClass(UserFavGenreId.class)
public class UserFavGenre {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "genre")
    private String genre;

    // Constructors, getters, and setters

    public UserFavGenre() {
    }

    public UserFavGenre(Long userId, String genre) {
        this.userId = userId;
        this.genre = genre;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
