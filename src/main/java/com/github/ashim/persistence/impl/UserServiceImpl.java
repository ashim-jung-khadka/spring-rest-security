package com.github.ashim.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.common.spec.SpecBuilder;
import com.github.ashim.persistence.common.utility.ServiceHelper;
import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.repo.UserRepository;
import com.github.ashim.persistence.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {

		String password = ServiceHelper.passwordEncoder(user.getPassword());
		user.setPassword(password);

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

	@Override
	public List<User> findAllBySpec(String search) {

		Specification<User> spec = SpecBuilder.build(search);
		return userRepository.findAll(spec);

	}

	@Override
	public List<User> search(String searchTerm) {
		return userRepository.search(searchTerm);
	}

}
