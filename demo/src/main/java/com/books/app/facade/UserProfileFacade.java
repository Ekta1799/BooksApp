package com.books.app.facade;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.books.app.model.User;
import com.books.app.model.Userprofile;
import com.books.app.pojo.UserProfileRequest;
import com.books.app.pojo.UserRequest;
import com.books.app.repository.BookRepository;
import com.books.app.repository.UserBookOwnedRepository;
import com.books.app.repository.UserBookWishRepository;
import com.books.app.repository.UserFavGenreRepository;
import com.books.app.repository.UserReadingPreferenceRepository;
import com.books.app.repository.UserRepository;
import com.books.app.repository.UserprofileRepository;
import com.books.app.services.UserProfileService;
import com.books.app.services.UserService;

@Component
public class UserProfileFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileFacade.class);

	@Autowired
	UserprofileRepository userprofileRepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserProfileService service;

	@Autowired
	UserFavGenreRepository userFavGenreRepo;

	@Autowired
	UserReadingPreferenceRepository userReadingPreferenceRepo;

	@Autowired
	UserBookOwnedRepository userBookOwnedRepo;

	@Autowired
	UserBookWishRepository userBookWishRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;
	
	public Userprofile getUserProfile(String firstName) {
		Userprofile resource = service.getUserProfile(firstName);
		return resource;
	}
	
	public boolean updateUserProfile(UserProfileRequest userProfileRequest) {
		
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

		Optional<User> userData = userRepository.findByUsername(username);
		User userVal = userData.get();

		if (userprofileRepo.existsByUserId(userVal.getId())) {

			Optional<Userprofile> user = userprofileRepo.findByUsername(username);
			Userprofile user1 = user.get();

			Set<String> genres = new HashSet<String>(userProfileRequest.getFavGenre());
			
			Set<String> readingPrefBooks = new HashSet<String>(userProfileRequest.getReadingPref());

			Set<String> bookWishlistBooks = new HashSet<String>(userProfileRequest.getBookWishList());

			Set<String> booksOwned = new HashSet<String>(userProfileRequest.getBookOwnedList());

			try {
				userFavGenreRepo.deleteUserFavoriteGenresByUserId(user1.getUser().getId());
				logger.debug("..." + user1);

				for (String newGenre : genres) {
					userFavGenreRepo.insertUserFavoriteGenreByUserId(user1.getUser().getId(), newGenre);
				}

				userReadingPreferenceRepo.deleteUserReadingPreferenceByUserId(user1.getUser().getId());
				for (String newBook : readingPrefBooks) {
					userReadingPreferenceRepo.insertUserReadingPrefernceByUserId(user1.getUser().getId(), newBook);
				}

				userBookOwnedRepo.deleteUserBookOwnedByUserId(user1.getUser().getId());
				for (String newBook : booksOwned) {
					userBookOwnedRepo.insertUserBookOwnedByUserId(user1.getUser().getId(), newBook);
				}

				userBookWishRepo.deleteUserBookWishByUserId(user1.getUser().getId());
				for (String newBook : bookWishlistBooks) {
					userBookWishRepo.insertUserBookWishByUserId(user1.getUser().getId(), newBook);
				}

				userprofileRepo.updateUserProfile(user1.getId(), firstName, lastName, phoneNo);
			} catch(Exception e) {
				logger.error("Could not update user profile : {}", e);
			}
			
		} else {
			logger.error("User profile does not exist for user ID: " + userVal.getId());
		}
		return true;
	}
	
	public boolean updateUser(UserRequest userRequest) {
		
		if(userService.existsByUsername(userRequest.getUsername())) {
			try {
				userService.updatePassword(encoder.encode(userRequest.getPassword()), userRequest.getUsername());
			} catch(Exception e) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
}
