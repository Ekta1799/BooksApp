package com.books.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.books.app.exception.GeneralException;
import com.books.app.model.User;
import com.books.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {

		return (User) userRepository.save(user);
	}

	public User findUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new GeneralException("User not found", HttpStatus.NOT_FOUND);
		}
	}

	public Boolean existsByUsername(String username) {

		return userRepository.findByUsername(username).isPresent();
	}
	
	public void updatePassword(String password, String username) {
		userRepository.updatePassword(password, username);
	}
	
	
}