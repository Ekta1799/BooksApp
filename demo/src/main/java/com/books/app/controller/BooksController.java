package com.books.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.app.facade.BooksFacade;
import com.books.app.model.Genres;
import com.books.app.pojo.BooksResource;
import com.books.app.repository.GenreRepository;
import com.books.app.repository.UserBookOwnedRepository;
import com.books.app.repository.UserBookWishRepository;
import com.books.app.repository.UserFavGenreRepository;
import com.books.app.repository.UserReadingPreferenceRepository;
import com.books.app.repository.UserRepository;
import com.books.app.repository.UserprofileRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BooksController {
	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	private BooksFacade facade;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	UserprofileRepository userprofileRepo;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserFavGenreRepository userFavGenreRepo;
	
	@Autowired
	UserReadingPreferenceRepository userReadingPreferenceRepo;
	
	@Autowired
	UserBookOwnedRepository userBookOwnedRepo;
	
	@Autowired
	UserBookWishRepository userBookWishRepo;
	
	//GET - books with filters
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/books", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> searchBooks(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "availability", required = false) Boolean availability,
            @RequestParam(value = "filter", required = false) String filter, HttpServletRequest request) {

        logger.info("Search books");
        
        // Map genre IDs to genre names
        Map<String, Long> genreMap = new HashMap<String, Long>();
        List<Genres> genres = genreRepository.findAll();
        for (Genres genree : genres) {
        	genreMap.put(genree.getGenre_name(), genree.getGenre_id());
        }
        Long genreId = genreMap.get(genre);
     
        // Call facade method to get books with optional search criteria
        List<BooksResource> books = facade.getBooks(genreId, author, title, availability);

        return ResponseEntity.ok(books);
    }

	//GET - books by id
	@RequestMapping(value = "/book/{id}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getEBPPlansByScope(@PathVariable Integer id) throws Exception {

		logger.info("book for given id " + id);

		BooksResource books = facade.getBookById(id);

		return ResponseEntity.ok(books);
	}
}
