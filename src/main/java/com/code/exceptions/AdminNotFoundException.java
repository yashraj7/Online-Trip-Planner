package com.code.exceptions;

public class AdminNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AdminNotFoundException() {
		super();
	}

	public AdminNotFoundException(String message) {
		super(message);
	}

}
