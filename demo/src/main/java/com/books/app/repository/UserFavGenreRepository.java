package com.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.books.app.model.UserFavGenre;

import jakarta.transaction.Transactional;

@Transactional
public interface UserFavGenreRepository extends JpaRepository<UserFavGenre, Long> {

	@Modifying
    @Query("DELETE FROM UserFavGenre ufg WHERE ufg.userId = :userId")
    void deleteUserFavoriteGenresByUserId(@Param("userId")Long userId);

    @Modifying
    @Query("INSERT INTO UserFavGenre (userId, genreId) VALUES (:userId, :genreId)")
    void insertUserFavoriteGenreByUserId(@Param("userId")Long userId, @Param("genreId")Long genreId);
}