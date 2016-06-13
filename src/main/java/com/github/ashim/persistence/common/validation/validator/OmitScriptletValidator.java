package com.github.ashim.persistence.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ashim.persistence.common.validation.annotation.OmitScriptlet;

public class OmitScriptletValidator implements ConstraintValidator<OmitScriptlet, String> {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private static final String OMIT_SCRIPTLET_PATTERN = "[<][/]?[\\w\\s]+[>]";

	@Override
	public void initialize(final OmitScriptlet constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String description, final ConstraintValidatorContext context) {

		if (description == null) {
			return true;
		}

		LOGGER.debug("OmitScriptletValidator ::: description : {}", description);

		Boolean status = description.matches(OMIT_SCRIPTLET_PATTERN);

		if (status) {
			return false;
		} else {
			return true;
		}
	}
}