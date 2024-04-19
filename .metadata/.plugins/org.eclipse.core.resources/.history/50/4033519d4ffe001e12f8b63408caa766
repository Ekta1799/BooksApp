package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

//    List<Book> findAllByCategory(Category name);
    
    @Query("SELECT b FROM Books b " +
            "WHERE (:genreId IS NULL OR b.genre_id = :genreId) " +
            "AND (:author IS NULL OR b.author = :author) " +
            "AND (:title IS NULL OR b.title = :title) " +
            "AND (:availability IS NULL OR b.availability = :availability)")
     List<Books> searchBooks(@Param("genreId") Long genreId,
                             @Param("author") String author,
                             @Param("title") String title,
                             @Param("availability") Boolean availability);

}