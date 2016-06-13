package com.github.ashim.web.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Error {

	private HttpStatus status;
	private Map<String, String> fieldErrors;

	public Error() {
	}

	public Error(HttpStatus status, String errorMessage) {
		fieldErrors = new HashMap<>();
		fieldErrors.put("error", errorMessage);
	}

	public Error(HttpStatus status, Map<String, String> fieldErrors) {
		this.status = status;
		this.fieldErrors = fieldErrors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}