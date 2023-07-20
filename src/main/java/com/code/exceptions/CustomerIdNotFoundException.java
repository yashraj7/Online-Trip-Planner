package com.code.exceptions;

public class CustomerIdNotFoundException extends Exception{
	public CustomerIdNotFoundException() {
		super();
	}

	public CustomerIdNotFoundException (String message) {
		super(message);
	}

}
