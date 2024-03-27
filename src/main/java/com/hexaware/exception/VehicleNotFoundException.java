package com.hexaware.exception;

/**
 * Custom exception class for handling cases when a vehicle is not found.
 */
public class VehicleNotFoundException extends Exception {

	String message;

	/**
	 * Constructs a VehicleNotFoundException with the specified error message.
	 *
	 * @param message The error message indicating why the vehicle was not found.
	 */
	public VehicleNotFoundException(String message) {

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
