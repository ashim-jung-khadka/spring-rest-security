package com.github.ashim.web.error;

public final class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1552997988720722589L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

	public ResourceNotFoundException(final Throwable cause) {
		super(cause);
	}

}
