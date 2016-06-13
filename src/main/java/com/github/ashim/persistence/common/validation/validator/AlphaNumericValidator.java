package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.AlphaNumeric;

public class AlphaNumericValidator implements ConstraintValidator<AlphaNumeric, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String ALPHA_NUMERIC_PATTERN = "^[\\w\\s][\\w\\s]+$";

	@Override
	public void initialize(final AlphaNumeric constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String alphaNumeric, final ConstraintValidatorContext context) {

		if (alphaNumeric == null) {
			return true;
		}

		LOGGER.debug("AlphaNumericValidator ::: alphaNumeric : {}", alphaNumeric);

		return alphaNumeric.matches(ALPHA_NUMERIC_PATTERN);
	}
}