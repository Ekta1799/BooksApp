package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Userprofile;

public interface UserprofileRepository extends JpaRepository<Userprofile, Long> {
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Userprofile u WHERE u.user.id = :userId")
	Boolean existsByUserId(@Param("userId") Long userId);

}
