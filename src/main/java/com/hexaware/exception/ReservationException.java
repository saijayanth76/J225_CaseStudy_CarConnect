package com.hexaware.exception;

/**
 * Custom exception class for handling reservation-related errors.
 */
public class ReservationException extends Exception {

	String message;

	/**
	 * Constructs a ReservationException with the specified error message.
	 *
	 * @param message The error message indicating the reservation error.
	 */
	public ReservationException(String message) {

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
