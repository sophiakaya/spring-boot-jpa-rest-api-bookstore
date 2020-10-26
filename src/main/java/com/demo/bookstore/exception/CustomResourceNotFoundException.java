package com.demo.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4537858315581850615L;

	public CustomResourceNotFoundException() {
		super();
	}

	public CustomResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomResourceNotFoundException(String message) {
		super(message);
	}

	public CustomResourceNotFoundException(Throwable cause) {
		super(cause);
	}
}
