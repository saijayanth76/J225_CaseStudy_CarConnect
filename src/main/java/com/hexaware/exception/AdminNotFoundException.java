package com.hexaware.exception;

/**
 * Custom exception class for handling cases when an admin is not found.
 */
public class AdminNotFoundException extends Exception {

	String message;

	/**
	 * Constructs an AdminNotFoundException with the specified error message.
	 *
	 * @param message The error message indicating why the admin was not found.
	 */
	public AdminNotFoundException(String message) {

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
