package com.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.app.model.Genres;

@Repository
public interface GenreRepository extends JpaRepository<Genres, Long> {

}
