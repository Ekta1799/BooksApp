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
    @Column(name = "genre_id")
    private Long genreId;

    // Constructors, getters, and setters

    public UserFavGenre() {
    }

    public UserFavGenre(Long userId, Long genreId) {
        this.userId = userId;
        this.genreId = genreId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
