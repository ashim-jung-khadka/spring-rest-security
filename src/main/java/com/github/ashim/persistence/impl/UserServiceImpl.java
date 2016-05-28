package com.github.ashim.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.repo.UserRepository;
import com.github.ashim.persistence.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
