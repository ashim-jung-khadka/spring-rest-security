package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void initialize(final ValidEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {

		if (email == null) {
			return true;
		}

		LOGGER.debug("EmailValidator ::: email : {}", email);

		return email.matches(EMAIL_PATTERN);
	}
}