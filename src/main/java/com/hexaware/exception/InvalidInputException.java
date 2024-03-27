package com.hexaware.exception;

/**
 * Custom exception class for handling invalid input errors.
 */
public class InvalidInputException extends Exception {

	String message;

	/**
	 * Constructs an InvalidInputException with the specified error message.
	 *
	 * @param message The error message indicating why the input is invalid.
	 */
	public InvalidInputException(String message) {

		this.message = message;
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
}
