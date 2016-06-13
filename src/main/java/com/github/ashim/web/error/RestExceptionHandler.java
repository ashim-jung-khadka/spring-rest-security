package com.github.ashim.web.error;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.ashim.web.dto.GenericResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	public RestExceptionHandler() {
		super();
	}

	// API

	// 400

	// 400
	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.error("400 Status Code", ex);
		final BindingResult result = ex.getBindingResult();
		final GenericResponse bodyOfResponse = new GenericResponse(result.getFieldErrors(), result.getGlobalErrors());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be ConstraintViolationException specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be DataIntegrityViolationException specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		String bodyOfResponse = "This should be HttpMessageNotReadableException specific";

		JsonMappingException jme = (JsonMappingException) ex.getCause();
		// System.out.println(jme.getLocalizedMessage());
		// System.out.println(jme.getMessage());

		bodyOfResponse += " : " + jme.getOriginalMessage();

		// JsonParseException jpe = (JsonParseException) ex.getCause();
		// System.out.println("---------------------------------");
		// System.out.println(jpe.getLocalizedMessage());
		// System.out.println("---------------------------------");
		// System.out.println(jpe.getMessage());
		// System.out.println("---------------------------------");
		// System.out.println(jpe.getOriginalMessage());
		// System.out.println("---------------------------------");

		// ex.getCause() instanceof JsonMappingException, JsonParseException //
		// for additional information later on

		return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());

		final String errorMsg = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final Error error = new Error(HttpStatus.BAD_REQUEST, errorMsg);
		return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
	}

	// @Override
	// protected ResponseEntity<Object> handleMethodArgumentNotValid(final
	// MethodArgumentNotValidException ex,
	// final HttpHeaders headers, final HttpStatus status, final WebRequest
	// request) {
	// final String bodyOfResponse = "This should be
	// MethodArgumentNotValidException specific";
	// return handleExceptionInternal(ex, bodyOfResponse, headers,
	// HttpStatus.BAD_REQUEST, request);
	// }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());

		Map<String, String> fieldErrors = new HashMap<>();
		// final List<String> errors = new ArrayList<String>();

		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
			// errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			fieldErrors.put(error.getObjectName(), error.getDefaultMessage());
			// errors.add(error.getObjectName() + ": " +
			// error.getDefaultMessage());
		}

		final Error error = new Error(HttpStatus.BAD_REQUEST, fieldErrors);
		return handleExceptionInternal(ex, error, headers, error.getStatus(), request);
	}

	// 403
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
		System.out.println("request" + request.getUserPrincipal());
		return new ResponseEntity<Object>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	// 404

	@ExceptionHandler(value = { EntityNotFoundException.class, ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be EntityNotFoundException or ResourceNotFoundException specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	// 409

	@ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be InvalidDataAccessApiUsageException or DataAccessException specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	// 412

	// 500

	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
	/* 500 */public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		final String bodyOfResponse = "This should be NullPointerException or IllegalArgumentException or IllegalStateException specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);

	}

}