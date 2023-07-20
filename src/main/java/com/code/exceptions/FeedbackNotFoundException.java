package com.code.exceptions;

public class FeedbackNotFoundException extends Exception {
	public FeedbackNotFoundException() {
		super();
	}
	public FeedbackNotFoundException(String message) {
		super(message);
	}
}