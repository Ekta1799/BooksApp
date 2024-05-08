package com.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.app.model.Books;
import com.books.app.model.Userprofile;

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
    
    @Query("SELECT u FROM Userprofile u " +
            "WHERE u.firstname = :firstname ")
     Userprofile getUserProfile(@Param("firstname") String firstname);

}