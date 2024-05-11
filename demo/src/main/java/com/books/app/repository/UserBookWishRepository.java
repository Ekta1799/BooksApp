package com.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.books.app.model.UserBookWishList;

import jakarta.transaction.Transactional;

@Transactional
public interface UserBookWishRepository extends JpaRepository<UserBookWishList, String> {

	@Modifying
    @Query("DELETE FROM UserBookWishList ufg WHERE ufg.userId = :userId")
    void deleteUserBookWishByUserId(@Param("userId")Long userId);

    @Modifying
    @Query("INSERT INTO UserBookWishList (userId, book) VALUES (:userId, :book)")
    void insertUserBookWishByUserId(@Param("userId")Long userId, @Param("book")String book);
}

