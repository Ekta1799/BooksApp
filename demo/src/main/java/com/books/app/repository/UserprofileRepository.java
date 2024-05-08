package com.books.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.books.app.model.Userprofile;

import jakarta.transaction.Transactional;

@Transactional
public interface UserprofileRepository extends JpaRepository<Userprofile, Long> {
	
	Optional<Userprofile> findByUsername(String username);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Userprofile u WHERE u.user.id = :userId")
	Boolean existsByUserId(@Param("userId") Long userId);
	
	@Modifying
    @Query(value = "UPDATE user_profile SET firstname = ?2 , lastname = ?3, phone_no = ?4 WHERE user_id = ?1", nativeQuery = true)
    void updateUserFavoriteGenres(Long userId, String firstname, String lastname, String phone_no);
	
	@Query("SELECT u FROM Userprofile u " +
            "WHERE u.firstname = :firstname ")
     Userprofile getUserProfile(@Param("firstname") String firstname);

}
