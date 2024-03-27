package com.hexaware.controller;

import java.sql.SQLException;

import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.Customer;

public interface ICustomerService {

	/**
     * Retrieves a customer's details based on the provided customer ID.
     *
     * @param customerId The ID of the customer.
     * @return The Customer object representing the customer with the specified ID.
     * @throws SQLException               If an SQL exception occurs during the process.
     * @throws DatabaseConnectionException If a database connection exception occurs during the process.
     * @throws CustomerNotFoundException  If the customer with the specified ID is not found.
     */
	public Customer getCustomerById(int customerId)
			throws SQLException, DatabaseConnectionException, CustomerNotFoundException;

	 /**
     * Retrieves a customer's details based on the provided username.
     *
     * @param userName The username of the customer.
     * @return The Customer object representing the customer with the specified username.
     * @throws DatabaseConnectionException If a database connection exception occurs during the process.
     * @throws SQLException               If an SQL exception occurs during the process.
     * @throws CustomerNotFoundException  If the customer with the specified username is not found.
     */
	public Customer getCustomerByUsername(String userName)
			throws DatabaseConnectionException, SQLException, CustomerNotFoundException;

	/**
     * Registers a new customer in the system.
     *
     * @param customer The Customer object representing the new customer to be registered.
     * @return True if the customer is registered successfully, false otherwise.
     * @throws DatabaseConnectionException If a database connection exception occurs during the process.
     */
	public boolean registerCustomer(Customer customer) throws DatabaseConnectionException;
	
	/**
     * Updates a customer's information based on the specified username and choice.
     *
     * @param username The username of the customer whose information is to be updated.
     * @param choice   The choice indicating which information to update.
     * @return True if the customer's information is updated successfully, false otherwise.
     * @throws SQLException               If an SQL exception occurs during the process.
     * @throws DatabaseConnectionException If a database connection exception occurs during the process.
     * @throws CustomerNotFoundException  If the customer with the specified username is not found.
     */
	public boolean updateCustomer(String username, int choice)
			throws SQLException, DatabaseConnectionException, CustomerNotFoundException;
	
	 /**
     * Deletes a customer based on the specified customer ID.
     *
     * @param customerId The ID of the customer to be deleted.
     * @return True if the customer is deleted successfully, false otherwise.
     * @throws DatabaseConnectionException If a database connection exception occurs during the process.
     * @throws SQLException               If an SQL exception occurs during the process.
     * @throws CustomerNotFoundException  If the customer with the specified ID is not found.
     */
	public boolean deleteCustomer(int customerId)
			throws DatabaseConnectionException, SQLException, CustomerNotFoundException;
	
	/**
     * Retrieves the customer ID based on the provided username.
     *
     * @param username The username of the customer.
     * @return The ID of the customer with the specified username.
     */
	public int getCustomerIdByUsername(String username);
}
