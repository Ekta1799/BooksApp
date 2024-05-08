package com.books.app.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.books.app.model.Userprofile;
import com.books.app.repository.UserprofileRepository;

public class UserProfileServiceImpl {
	
	@Autowired
	UserprofileRepository userprofileRepo;
	
	public void createUserProfile(Userprofile userprofile) {
		userprofileRepo.save(userprofile);
	}


}
