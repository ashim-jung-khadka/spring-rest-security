package com.github.ashim.persistence.service;

import com.github.ashim.persistence.entity.User;

public interface UserService {

	void save(User user);

	User findById(int id);

	User findByUsername(String username);

}