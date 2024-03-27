package com.hexaware.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.hexaware.dao.CustomerDao;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.Customer;

/**
 * This class implements the ICustomerService interface and provides methods for
 * customer-related operations.
 */
public class CustomerService implements ICustomerService {

	/** The CustomerDao instance for customer-related database operations. */
	CustomerDao custDao;
	/** The Customer instance for customer-related operations. */
	Customer customer;

	/** The Scanner instance for taking user input. */
	Scanner in = new Scanner(System.in);

	/**
	 * Retrieves a customer by their ID from the database.
	 *
	 * @param customerId The ID of the customer to retrieve.
	 * @return The customer object if found, otherwise null.
	 */
	@Override
	public Customer getCustomerById(int customerId) {

		try {

			custDao = new CustomerDao();
			customer = custDao.getCustomerById(customerId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		return customer;
	}

	/**
	 * Retrieves a customer by their username from the database.
	 *
	 * @param userName The username of the customer to retrieve.
	 * @return The customer object if found, otherwise null.
	 */
	@Override
	public Customer getCustomerByUsername(String userName) {

		try {

			custDao = new CustomerDao();
			customer = custDao.getCustomerByUsername(userName);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		return customer;
	}

	/**
	 * Registers a new customer in the system.
	 *
	 * @param customer The customer object containing registration details.
	 * @return True if registration is successful, false otherwise.
	 */
	@Override
	public boolean registerCustomer(Customer customer) {

		custDao = new CustomerDao();
		boolean isRegistrationSuccess = false;

		System.out.println("Enter the Details");
		System.out.println("--------------------");
		System.out.println("Enter the first name : ");
		String firstName = in.next();
		System.out.println("Enter the last name : ");
		String lastName = in.next();
		System.out.println("Enter the email : ");
		String email = in.next();
		System.out.println("Enter the phone number : ");
		String phoneNumber = in.next();
		System.out.println("Enter the address : ");
		in.nextLine();
		String address = in.nextLine();
		System.out.println("Enter the username : ");
		String username = in.next();
		System.out.println("Set password : ");
		String password = in.next();

		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setAddress(address);
		customer.setUserName(username);
		customer.setPassword(password);

		try {

			isRegistrationSuccess = custDao.registerCustomer(customer);

		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		}

		return isRegistrationSuccess;
	}

	/**
	 * Updates a customer's information based on the specified choice.
	 *
	 * @param username The username of the customer to update.
	 * @param choice   The choice indicating the field to update.
	 * @return True if update is successful, false otherwise.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If a database connection error occurs.
	 * @throws CustomerNotFoundException   If the customer is not found.
	 */
	@Override
	public boolean updateCustomer(String username, int choice)
			throws SQLException, DatabaseConnectionException, CustomerNotFoundException {

		custDao = new CustomerDao();
		String updateValue = null;
		boolean isUpdated = false;

		switch (choice) {

		case 1: {

			System.out.println("Enter the new email to update : ");
			updateValue = in.next();
			isUpdated = custDao.updateCustomer(username, "email", updateValue);
			break;
		}
		case 2: {

			System.out.println("Enter the new phone number for updation");
			updateValue = in.next();
			isUpdated = custDao.updateCustomer(username, "phoneNumber", updateValue);
			break;
		}
		case 3: {

			System.out.println("Enter the updated address");
			in.nextLine();
			updateValue = in.nextLine();
			isUpdated = custDao.updateCustomer(username, "address", updateValue);
			break;
		}
		case 4: {

			System.out.println("Enter the new password");
			updateValue = in.next();
			isUpdated = custDao.updateCustomer(username, "password", updateValue);
			break;
		}
		}

		return isUpdated;
	}

	/**
	 * Deletes a customer from the system based on their ID.
	 *
	 * @param customerId The ID of the customer to delete.
	 * @return True if deletion is successful, false otherwise.
	 */
	@Override
	public boolean deleteCustomer(int customerId) {

		custDao = new CustomerDao();
		boolean isCustomerDeleted = false;

		try {

			isCustomerDeleted = custDao.deleteCustomer(customerId);

		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}

		return isCustomerDeleted;

	}

	/**
	 * Retrieves the customer ID based on the username.
	 *
	 * @param username The username of the customer.
	 * @return The customer ID if found, otherwise 0.
	 */
	@Override
	public int getCustomerIdByUsername(String username) {

		int customerId = 0;
		try {
			custDao = new CustomerDao();
			customerId = custDao.getCustomerIdByUsername(username);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		}
		return customerId;
	}

}
