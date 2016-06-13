package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.Username;

public class UsernameValidator implements ConstraintValidator<Username, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String USERNAME_PATTERN = "^[\\w]+$";

	@Override
	public void initialize(final Username constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String username, final ConstraintValidatorContext context) {

		if (username == null) {
			return true;
		}

		LOGGER.debug("UsernameValidator ::: username : {}", username);

		return username.matches(USERNAME_PATTERN);
	}
}