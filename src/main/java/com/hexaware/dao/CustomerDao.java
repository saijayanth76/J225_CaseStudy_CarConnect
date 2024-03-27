package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.Customer;
import com.hexaware.util.DBConnUtil;

/**
 * This class provides methods to interact with the Customer database table.
 */
public class CustomerDao {

	/**
	 * Retrieves a customer by their ID from the database.
	 *
	 * @param customerId The ID of the customer to retrieve.
	 * @return The customer object if found.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws CustomerNotFoundException   If the customer with the given ID is not
	 *                                     found.
	 */
	public Customer getCustomerById(int customerId)
			throws SQLException, DatabaseConnectionException, CustomerNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from customer where customerId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, customerId);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			int customerid = rs.getInt("customerId");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phonenumber = rs.getString("phoneNumber");
			String address = rs.getString("address");
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			LocalDate registrationDate = rs.getDate("registrationDate").toLocalDate();

			Customer customer = new Customer();

			customer.setCustomerId(customerid);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
			customer.setPhoneNumber(phonenumber);
			customer.setAddress(address);
			customer.setUserName(userName);
			customer.setPassword(password);
			customer.setRegistrationdate(registrationDate);

			DBConnUtil.dbClose();
			return customer;
		}

		DBConnUtil.dbClose();
		throw new CustomerNotFoundException("Invalid Customer Id");
	}

	/**
	 * Retrieves a customer by their username from the database.
	 *
	 * @param userName The username of the customer to retrieve.
	 * @return The customer object if found.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws SQLException                If a database access error occurs.
	 * @throws CustomerNotFoundException   If the customer with the given username
	 *                                     is not found.
	 */
	public Customer getCustomerByUsername(String userName)
			throws DatabaseConnectionException, SQLException, CustomerNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from customer where userName = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			int customerId = rs.getInt("customerId");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phonenumber = rs.getString("phoneNumber");
			String address = rs.getString("address");
			String user_name = rs.getString("userName");
			String password = rs.getString("password");
			LocalDate registrationDate = rs.getDate("registrationDate").toLocalDate();

			Customer customer = new Customer();

			customer.setCustomerId(customerId);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
			customer.setPhoneNumber(phonenumber);
			customer.setAddress(address);
			customer.setUserName(user_name);
			customer.setPassword(password);
			customer.setRegistrationdate(registrationDate);

			DBConnUtil.dbClose();
			return customer;
		}

		DBConnUtil.dbClose();
		throw new CustomerNotFoundException("Customer with username" + " " + userName + " " + "not found");
	}

	/**
	 * Registers a new customer in the database.
	 *
	 * @param customer The customer object to register.
	 * @return True if the registration is successful, false otherwise.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public boolean registerCustomer(Customer customer) throws DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "INSERT INTO Customer (FirstName, LastName, Email, PhoneNumber, Address, Username, Password,RegistrationDate)"
				+ " VALUES (?, ?, ?, ?, ?, ?,?, ?)";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhoneNumber());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getUserName());
			ps.setString(7, customer.getPassword());
			ps.setDate(8, Date.valueOf(LocalDate.now()));

			int rowsAffected = ps.executeUpdate();
			System.out.println(rowsAffected);

			DBConnUtil.dbClose();
			if (rowsAffected == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Updates a customer's information in the database.
	 *
	 * @param username    The username of the customer to update.
	 * @param updateField The field to update (e.g., FirstName, LastName, Email,
	 *                    etc.).
	 * @param updateValue The new value for the field.
	 * @return True if the update is successful, false otherwise.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws CustomerNotFoundException   If the customer with the given username
	 *                                     is not found.
	 */
	public boolean updateCustomer(String username, String updateField, String updateValue)
			throws SQLException, DatabaseConnectionException, CustomerNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "update Customer set updateField = ? where userName = ?";
		StringBuilder builder = new StringBuilder(query);

		builder.replace(20, 31, updateField);
		PreparedStatement ps = conn.prepareStatement(builder.toString());

		ps.setString(1, updateValue);
		ps.setString(2, username);
		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();

		if (rowsAffected == 1) {
			return true;
		}

		throw new CustomerNotFoundException("Invalid Customer id to do updation");

	}

	/**
	 * Deletes a customer from the database by their ID.
	 *
	 * @param customerId The ID of the customer to delete.
	 * @return True if the deletion is successful, false otherwise.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws SQLException                If a database access error occurs.
	 * @throws CustomerNotFoundException   If the customer with the given ID is not
	 *                                     found.
	 */
	public boolean deleteCustomer(int customerId)
			throws DatabaseConnectionException, SQLException, CustomerNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "delete from customer where customerId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, customerId);
		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();

		if (rowsAffected != 0) {
			return true;
		}

		throw new CustomerNotFoundException("Invalid Customer Id");
	}

	/**
	 * Retrieves the customer ID by their username from the database.
	 *
	 * @param username The username of the customer to retrieve the ID for.
	 * @return The customer ID if found; returns 0 if no matching customer is found.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public int getCustomerIdByUsername(String username) throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select customerId from customer where userName = ?";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		int customerId = 0;

		if (rs.next()) {
			customerId = rs.getInt(1);

		}
		return customerId;
	}

}
