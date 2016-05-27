package com.github.ashim.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.entity.UserProfile;
import com.github.ashim.persistence.repo.UserProfileRepository;
import com.github.ashim.persistence.service.UserProfileService;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public List<UserProfile> findAll() {
		return userProfileRepository.findAll();
	}

	@Override
	public UserProfile findByType(String type) {
		return userProfileRepository.findByType(type);
	}

	@Override
	public UserProfile findById(int id) {
		return userProfileRepository.findById(id);
	}
}
