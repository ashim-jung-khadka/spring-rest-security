package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.PasswordMatches;
import com.github.ashim.web.dto.AuthDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Object obj, final ConstraintValidatorContext context) {

		if (obj == null) {
			return false;
		}

		LOGGER.debug("PasswordMatchesValidator ::: user : {}", obj);

		final AuthDto user = (AuthDto) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}

}