package com.github.ashim.persistence.service;

import java.util.List;

import com.github.ashim.persistence.entity.UserProfile;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
