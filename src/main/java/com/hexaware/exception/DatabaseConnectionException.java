package com.hexaware.exception;

/**
 * Custom exception class for handling database connection errors.
 */
public class DatabaseConnectionException extends Exception {

	String message;

	/**
	 * Constructs a DatabaseConnectionException with the specified error message.
	 *
	 * @param message The error message indicating the database connection error.
	 */
	public DatabaseConnectionException(String message) {

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
