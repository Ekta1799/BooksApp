package com.example.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.facade.BooksFacade;
import com.example.model.Books;
import com.example.model.Genres;
import com.example.model.User;
import com.example.model.Userprofile;
import com.example.pojo.BooksResource;
import com.example.pojo.MessageResponse;
import com.example.pojo.UserProfileRequest;
import com.example.repository.BookRepository;
import com.example.repository.GenreRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserprofileRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BooksController {
	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	private BooksFacade facade;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	UserprofileRepository userprofileRepo;

	@Autowired
	UserRepository userRepository;
	
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

	@RequestMapping(value = "/book/{id}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getEBPPlansByScope(@PathVariable Integer id) throws Exception {

		logger.info("books");

		BooksResource books = facade.getBookById(id);

		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<BooksResource> responseEntity = new ResponseEntity(books, headers, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/userProfile")
	public ResponseEntity<?> saveUserProfile(@RequestBody UserProfileRequest userProfileRequest) {

		String firstName = null;
		String lastName = null;
		String username = null;
		String phoneNo = null;

		if (userProfileRequest.getUsername() != null) {
			username = userProfileRequest.getUsername();
		}
		if (userProfileRequest.getFirstName() != null) {
			firstName = userProfileRequest.getFirstName();
		}
		if (userProfileRequest.getLastName() != null) {
			lastName = userProfileRequest.getLastName();
		}
		if (userProfileRequest.getPhoneNo() != null) {
			phoneNo = userProfileRequest.getPhoneNo();
		}
		
		Optional<User> user = userRepository.findByUsername(username);
		User user1 = user.get();
		
		if(userprofileRepo.existsByUserId(user1.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User profile already exists for user ID: " + user1.getId());
		}

		// Map genre IDs to genre names
		Map<String, Genres> genreMap = new HashMap<String, Genres>();
		List<Genres> genres = genreRepository.findAll();
		for (Genres genre : genres) {
			genreMap.put(genre.getGenre_name(), genre);
		}
		Set<Genres> genreIds = new HashSet<Genres>();
		List<String> genreNames = userProfileRequest.getFavGenre();
		for (String genre : genreNames) {
			genreIds.add(genreMap.get(genre));
		}

		Map<String, Books> BookMap = new HashMap<String, Books>();
		List<Books> books = bookRepository.findAll();
		for (Books book : books) {
			BookMap.put(book.getTitle(), book);
		}
		Set<Books> readingPrefBookIds = new HashSet<Books>();
		List<String> readingPrefBookNames = userProfileRequest.getReadingPref();
		for (String book : readingPrefBookNames) {
			readingPrefBookIds.add(BookMap.get(book));
		}

		Set<Books> bookWishlistBookIds = new HashSet<Books>();
		List<String> bookWishlistBookNames = userProfileRequest.getBookWishList();
		for (String book : bookWishlistBookNames) {
			bookWishlistBookIds.add(BookMap.get(book));
		}

		Set<Books> bookOwnedIds = new HashSet<Books>();
		List<String> bookOwnedNames = userProfileRequest.getBookOwnedList();
		for (String book : bookOwnedNames) {
			bookOwnedIds.add(BookMap.get(book));
		}

		Userprofile userProfile = new Userprofile();
		userProfile.setUser(user1);
		userProfile.setUsername(username);
		userProfile.setFirstname(firstName);
		userProfile.setLastname(lastName);
		userProfile.setPhoneNo(phoneNo);
		userProfile.setUserReadingPreferences(readingPrefBookIds);
		userProfile.setFavoriteGenres(genreIds);
		userProfile.setUserBookWishList(bookWishlistBookIds);
		userProfile.setUserBookOwnedList(bookOwnedIds);
		
		logger.debug("--->" +readingPrefBookNames);
		logger.debug("--->" +readingPrefBookIds);
		logger.debug("--->" +userProfile);

		userprofileRepo.save(userProfile);

		return ResponseEntity.ok(new MessageResponse("User Profile Updated successfully!"));
	}

}