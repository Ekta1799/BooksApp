package com.books.app.services;

import com.books.app.model.Userprofile;

public interface UserProfileService {
	public void createUserProfile(Userprofile userProfile);

	public Userprofile getUserProfile(String firstName);
	
}