package com.github.ashim.persistence.common.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.ashim.persistence.common.validation.validator.AlphaNumericValidator;

@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AlphaNumericValidator.class)
@Documented
public @interface AlphaNumeric {

	String message() default "may contain only alphabet, number and whitespace";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}