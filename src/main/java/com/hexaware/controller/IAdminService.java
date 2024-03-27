package com.hexaware.controller;

import java.sql.SQLException;

import com.hexaware.exception.AdminNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.InvalidInputException;
import com.hexaware.model.Admin;

public interface IAdminService {

	/**
     * Retrieves an admin by username.
     *
     * @param username The username of the admin to retrieve.
     * @return The Admin object corresponding to the given username.
     * @throws SQLException              If a SQL exception occurs while accessing the database.
     * @throws DatabaseConnectionException If a database connection exception occurs.
     * @throws AdminNotFoundException     If the admin with the specified username is not found.
     */
	public Admin getAdminByUsername(String username)
			throws SQLException, DatabaseConnectionException, AdminNotFoundException;

	/**
     * Retrieves an admin by ID.
     *
     * @param adminId The ID of the admin to retrieve.
     * @return The Admin object corresponding to the given ID.
     * @throws SQLException              If a SQL exception occurs while accessing the database.
     * @throws DatabaseConnectionException If a database connection exception occurs.
     * @throws AdminNotFoundException     If the admin with the specified ID is not found.
     */
	public Admin getAdminById(int adminId) throws SQLException, DatabaseConnectionException, AdminNotFoundException;

	/**
     * Registers a new admin.
     *
     * @param admin The Admin object containing the details to be registered.
     * @return True if the admin is registered successfully, false otherwise.
     * @throws SQLException              If a SQL exception occurs while accessing the database.
     * @throws DatabaseConnectionException If a database connection exception occurs.
     * @throws InvalidInputException      If the input provided for registration is invalid.
     */
	public boolean registerAdmin(Admin admin) throws SQLException, DatabaseConnectionException, InvalidInputException;

	/**
     * Updates the information of an existing admin.
     *
     * @param username The username of the admin to be updated.
     * @param choice   The choice representing the field to be updated.
     * @return True if the admin information is updated successfully, false otherwise.
     * @throws SQLException              If a SQL exception occurs while accessing the database.
     * @throws DatabaseConnectionException If a database connection exception occurs.
     * @throws AdminNotFoundException     If the admin with the specified username is not found.
     */
	public boolean updateAdmin(String username, int choice)
			throws SQLException, DatabaseConnectionException, AdminNotFoundException;

	/**
     * Deletes an admin based on the admin ID.
     *
     * @param adminId The ID of the admin to be deleted.
     * @throws SQLException              If a SQL exception occurs while accessing the database.
     * @throws DatabaseConnectionException If a database connection exception occurs.
     * @throws AdminNotFoundException     If the admin with the specified ID is not found.
     */
	public void deleteAdmin(int adminId) throws SQLException, DatabaseConnectionException, AdminNotFoundException;
}
