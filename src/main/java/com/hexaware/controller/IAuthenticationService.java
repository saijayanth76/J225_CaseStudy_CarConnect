package com.hexaware.controller;

import com.hexaware.exception.AuthenticationException;

public interface IAuthenticationService {

	/**
	 * Checks if a customer exists in the system based on the provided username and
	 * password.
	 *
	 * @param username The username of the customer.
	 * @param password The password of the customer.
	 * @return True if the customer exists and the provided password matches, false
	 *         otherwise.
	 * @throws AuthenticationException If an authentication exception occurs during
	 *                                 the process.
	 */
	public boolean isCustomerExists(String username, String password) throws AuthenticationException;

	/**
	 * Checks if an admin exists in the system based on the provided username and
	 * password.
	 *
	 * @param username The username of the admin.
	 * @param password The password of the admin.
	 * @return True if the admin exists and the provided password matches, false
	 *         otherwise.
	 * @throws AuthenticationException If an authentication exception occurs during
	 *                                 the process.
	 */
	public boolean isAdminExists(String username, String password) throws AuthenticationException;
}
