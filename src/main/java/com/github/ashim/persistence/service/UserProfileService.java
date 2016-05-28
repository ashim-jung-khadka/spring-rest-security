package com.github.ashim.persistence.service;

import java.util.List;

import com.github.ashim.persistence.entity.UserProfile;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByProfileType(String type);
	
	UserProfile findById(int id);
}
