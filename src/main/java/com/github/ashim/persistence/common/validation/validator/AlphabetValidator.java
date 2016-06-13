package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.Alphabet;

public class AlphabetValidator implements ConstraintValidator<Alphabet, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String ALPHABETS_PATTERN = "^[a-zA-Z\\s][a-zA-Z\\s]+$";

	@Override
	public void initialize(final Alphabet constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String alphabet, final ConstraintValidatorContext context) {

		if (alphabet == null) {
			return true;
		}

		LOGGER.debug("AlphabetValidator ::: alphabet {}", alphabet);

		return alphabet.matches(ALPHABETS_PATTERN);
	}
}