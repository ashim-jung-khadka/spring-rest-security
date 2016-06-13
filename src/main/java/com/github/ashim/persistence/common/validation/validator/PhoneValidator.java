package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String PHONE_PATTERN = "^([0-9()-]+){7,12}$";

	@Override
	public void initialize(Phone constraintAnnotation) {
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext cxt) {

		if (phoneNumber == null) {
			return true;
		}

		LOGGER.debug("PhoneValidator ::: phoneNumber : {}", phoneNumber);

		return phoneNumber.matches(PHONE_PATTERN);
	}
}