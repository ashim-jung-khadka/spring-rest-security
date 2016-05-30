package com.github.ashim.persistence.service;

import java.util.List;

import com.github.ashim.persistence.entity.User;

public interface UserService {

	void save(User user);

	User findById(int id);

	User findByUserName(String username);

	public List<User> findAllBySpec(String search);

}