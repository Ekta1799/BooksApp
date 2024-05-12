package com.books.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.app.facade.BooksFacade;
import com.books.app.pojo.BooksResource;
import com.books.app.pojo.MessageResponse;
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
	@CrossOrigin(origins = "*", exposedHeaders = "**")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
	@RequestMapping(value = "/books", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> searchBooks(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "availability", required = false) Boolean availability,
            @RequestParam(value = "filter", required = false) String filter, HttpServletRequest request) {

        logger.info("Search books");
     
        // Call facade method to get books with optional search criteria
        List<BooksResource> books = facade.getBooks(genre, author, title, availability);

        return ResponseEntity.ok(books);
    }

	//POST - insert books into store 
	@CrossOrigin(origins = "*", exposedHeaders = "**")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
	@RequestMapping(value = "/books", method = { RequestMethod.POST }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> insertBooks(@RequestBody BooksResource book) {

        logger.info("Create books");
     
        // Call facade method to get books with optional search criteria
        facade.createBooks(book);

        return ResponseEntity.ok(new MessageResponse("Book successfully added into the book store!"));
    }	
	
	//GET - books by username
	@CrossOrigin(origins = "*", exposedHeaders = "**")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/books/{username}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getBooksForUsersOtherThanUsername(@PathVariable("username") String username) {

		logger.info("book for given username " + username);

		List<BooksResource> books = facade.getBooksForUsersOtherThanUsername(username);

		return ResponseEntity.ok(books);
	}
	
}
