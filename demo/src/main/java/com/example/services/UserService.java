package com.example.services;

import com.example.model.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User findUserByUsername(String username);
	
	public Boolean existsByUsername(String username);

}
