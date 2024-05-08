package com.books.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.app.model.Userprofile;
import com.books.app.repository.UserprofileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserprofileRepository userprofileRepo;
	
	public void createUserProfile(Userprofile userprofile) {
		userprofileRepo.save(userprofile);
	}

	public Userprofile getUserProfile(String firstName) {

		Userprofile res = new Userprofile();

		res = userprofileRepo.getUserProfile(firstName);

		return res;
	}


}
