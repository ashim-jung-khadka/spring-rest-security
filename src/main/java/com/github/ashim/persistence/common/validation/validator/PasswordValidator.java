package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[\\w$@$!%*?&]{8,}";

	@Override
	public void initialize(final Password constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {

		if (password == null || password.isEmpty()) {
			return false;
		}

		LOGGER.debug("PasswordValidator ::: password : {}", password);

		return password.matches(PASSWORD_PATTERN);
	}
}