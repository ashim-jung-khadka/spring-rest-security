package com.github.ashim.web.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.User;
import com.github.ashim.persistence.service.UserService;
import com.github.ashim.web.dto.SecurityRole;
import com.github.ashim.web.dto.UserDTO;
import com.github.ashim.web.util.SecurityContextUtil;

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

	@RequestMapping(value = "/spec", method = RequestMethod.GET)
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

	@Resource
	private SecurityContextUtil securityContextUtil;

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public UserDTO getLoggedInUser() {
		LOGGER.debug("Getting logged in user.");
		UserDetails principal = securityContextUtil.getPrincipal();
		return createDTO(principal);
	}

	private UserDTO createDTO(UserDetails principal) {
		UserDTO dto = null;
		if (principal != null) {
			String username = principal.getUsername();
			SecurityRole role = getRole(principal.getAuthorities());

			dto = new UserDTO(username, role);
		}

		LOGGER.debug("Created user dto: {}", dto);

		return dto;
	}

	private SecurityRole getRole(Collection<? extends GrantedAuthority> authorities) {
		LOGGER.debug("Getting role from authorities: {}", authorities);

		Iterator<? extends GrantedAuthority> authority = authorities.iterator();
		GrantedAuthority a = authority.next();

		return SecurityRole.valueOf(a.getAuthority());
	}

}