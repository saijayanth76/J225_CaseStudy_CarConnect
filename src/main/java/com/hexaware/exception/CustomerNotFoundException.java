package com.hexaware.exception;

/**
 * Custom exception class for handling cases when a customer is not found.
 */
public class CustomerNotFoundException extends Exception {

	String message;

	/**
	 * Constructs a CustomerNotFoundException with the specified error message.
	 *
	 * @param message The error message indicating why the customer was not found.
	 */
	public CustomerNotFoundException(String message) {

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
