package com.github.ashim.persistence.common.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.Numeric;

public class NumericValidator implements ConstraintValidator<Numeric, Integer> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private Pattern pattern;
	private Matcher matcher;
	private static final String NUMERIC_PATTERN = "^[\\d]+$";

	@Override
	public void initialize(final Numeric constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Integer number, final ConstraintValidatorContext context) {

		if (number == null) {
			return true;
		}

		LOGGER.debug("NumericValidator ::: number : {}", number);

		pattern = Pattern.compile(NUMERIC_PATTERN);
		matcher = pattern.matcher(number.toString());
		return matcher.matches();
	}
}