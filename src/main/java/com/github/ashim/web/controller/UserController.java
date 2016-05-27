package com.github.ashim.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> getUsers() {
		LOGGER.info("debug mode");

		return new ResponseEntity<>(userService.findById(1), HttpStatus.OK);
	}

}