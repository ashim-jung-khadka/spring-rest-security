package com.github.ashim.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private UserService userService;

	@Value("${project.name}")
	String projectName;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> getUsers() {
		LOGGER.info("debug mode - " + projectName);

		return new ResponseEntity<>(userService.findById(1), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/spec")
	public List<User> findAllBySpecification(@RequestParam(value = "search", required = false) String search) {

		// URL SAMPLE
		// ?search=status:inactive
		// ?search=status!inactive
		// ?search=id>3
		// ?search=id<3
		// ?search=userName~ashim
		// ?search=email:*gmail*
		// ?search=email:*com
		// ?search=email:ashim*

		return userService.findAllBySpec(search);
	}

}