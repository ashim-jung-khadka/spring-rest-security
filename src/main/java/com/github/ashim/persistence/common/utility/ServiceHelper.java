package com.github.ashim.persistence.common.utility;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ServiceHelper {

	public static String passwordEncoder(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);

		return encodedPassword;

	}

	public static String randomString() {

		String randomStr = UUID.randomUUID().toString();
		return randomStr;

	}

}
