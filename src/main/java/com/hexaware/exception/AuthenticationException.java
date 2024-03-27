package com.hexaware.exception;

/**
 * Custom exception class for handling authentication-related errors.
 */
public class AuthenticationException extends Exception {

	String message;
	boolean value;

	/**
	 * Constructs an AuthenticationException with the specified error message and
	 * value.
	 *
	 * @param value   The boolean value associated with the exception.
	 * @param message The error message indicating the authentication error.
	 */
	public AuthenticationException(boolean value, String message) {

		this.message = message;
		this.value = value;
	}

	/**
	 * Retrieves the error message associated with this exception.
	 *
	 * @return The error message.
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Retrieves the boolean value associated with this exception.
	 *
	 * @return The boolean value.
	 */
	public boolean getValue() {
		return value;
	}

}
