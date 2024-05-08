package com.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.books.app.model.UserReadingPreference;

import jakarta.transaction.Transactional;

@Transactional
public interface UserReadingPreferenceRepository extends JpaRepository<UserReadingPreference, Long> {

	@Modifying
    @Query("DELETE FROM UserReadingPreference ufg WHERE ufg.userId = :userId")
    void deleteUserReadingPreferenceByUserId(@Param("userId")Long userId);

    @Modifying
    @Query("INSERT INTO UserReadingPreference (userId, bookId) VALUES (:userId, :bookId)")
    void insertUserReadingPrefernceByUserId(@Param("userId")Long userId, @Param("bookId")Long bookId);
}
