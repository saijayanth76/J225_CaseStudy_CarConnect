package com.hexaware.controller;

import com.hexaware.exception.AuthenticationException;
import com.hexaware.model.Admin;
import com.hexaware.model.Customer;

/**
 * This class implements the IAuthenticationService interface and provides
 * methods for authenticating customers and admins.
 */
public class AuthenticationService implements IAuthenticationService {

	/** The CustomerService instance for customer-related operations. */
	CustomerService custService;
	
	/** The AdminService instance for admin-related operations. */
	
	AdminService adminService;
	/** The Customer instance for customer-related operations. */
	
	Customer customer;
	/** The Admin instance for admin-related operations. */
	Admin admin;

	/**
	 * Checks if a customer exists in the system based on the provided username and
	 * password.
	 *
	 * @param username The username of the customer.
	 * @param password The password of the customer.
	 * @return True if the customer exists and the password matches, false
	 *         otherwise.
	 * @throws AuthenticationException If authentication fails due to incorrect
	 *                                 username or password.
	 */
	@Override
	public boolean isCustomerExists(String username, String password) throws AuthenticationException {

		custService = new CustomerService();
		customer = custService.getCustomerByUsername(username);
		if (customer.getPassword().equals(password.trim())) {
			return true;
		} else {

			throw new AuthenticationException(false, "Authentication Failed!\n Incorrect username or password");
		}

	}

	/**
	 * Checks if an admin exists in the system based on the provided username and
	 * password.
	 *
	 * @param username The username of the admin.
	 * @param password The password of the admin.
	 * @return True if the admin exists and the password matches, false otherwise.
	 * @throws AuthenticationException If authentication fails due to incorrect
	 *                                 username or password.
	 */
	@Override
	public boolean isAdminExists(String username, String password) throws AuthenticationException {

		adminService = new AdminService();
		admin = adminService.getAdminByUsername(username);

		if (admin.getPassword().equals(password.trim())) {
			return true;
		} else {
			throw new AuthenticationException(false, "Authentication Failed!\n Incorrect username or password");
		}

	}

}
