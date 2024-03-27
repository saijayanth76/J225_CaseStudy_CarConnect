package com.hexaware.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.hexaware.dao.AdminDao;
import com.hexaware.exception.AdminNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.InvalidInputException;
import com.hexaware.model.Admin;

/**
 * This class implements the IAdminService interface and provides methods to
 * interact with the AdminDao class for admin-related operations.
 */
public class AdminService implements IAdminService {

	/** The AdminDao instance for database operations. */
	AdminDao adminDao;
	/** The Admin instance for admin-related operations. */
	Admin admin;

	/** Scanner object for user input. */
	Scanner in = new Scanner(System.in);

	/**
	 * Retrieves an admin by username.
	 *
	 * @param userName The username of the admin to retrieve.
	 * @return The admin object retrieved from the database.
	 */
	@Override
	public Admin getAdminByUsername(String userName) {

		try {

			adminDao = new AdminDao();
			admin = adminDao.getAdminByUsername(userName);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (AdminNotFoundException e) {
			e.printStackTrace();
		}
		return admin;
	}

	/**
	 * Retrieves an admin by ID.
	 *
	 * @param adminId The ID of the admin to retrieve.
	 * @return The admin object retrieved from the database.
	 */
	@Override
	public Admin getAdminById(int adminId) {

		adminDao = new AdminDao();

		Admin admin = null;
		try {
			admin = adminDao.getAdminById(adminId);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		} catch (AdminNotFoundException e) {

			e.printStackTrace();
		}

		return admin;
	}

	/**
	 * Registers a new admin in the system.
	 *
	 * @param admin The admin object to be registered.
	 * @return True if registration is successful, false otherwise.
	 * @throws SQLException                If a database error occurs.
	 * @throws DatabaseConnectionException If a database connection error occurs.
	 * @throws InvalidInputException       If invalid input is provided.
	 */
	@Override
	public boolean registerAdmin(Admin admin) throws SQLException, DatabaseConnectionException, InvalidInputException {

		boolean isRegistrationSuccess = false;

		adminDao = new AdminDao();

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
		System.out.println("Enter the username : ");
		String username = in.next();
		System.out.println("Set password : ");
		String password = in.next();
		System.out.println("Enter the role: ");
		in.nextLine();
		String role = in.nextLine();

		admin.setFirstName(firstName);
		admin.setLastName(lastName);
		admin.setEmail(email);
		admin.setPhoneNumber(phoneNumber);
		admin.setUserName(username);
		admin.setPassword(password);
		admin.setRole(role);
		admin.setJoinDate(LocalDate.now());

		try {

			isRegistrationSuccess = adminDao.registerAdmin(admin);

		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (isRegistrationSuccess) {
			return isRegistrationSuccess;
		} else {
			throw new InvalidInputException("Invalid details entered");
		}

	}

	/**
	 * Updates admin details based on the choice provided.
	 *
	 * @param username The username of the admin to update.
	 * @param choice   The choice of update (1 for email, 2 for phone number, 3 for
	 *                 password).
	 * @return True if update is successful, false otherwise.
	 * @throws SQLException                If a database error occurs.
	 * @throws DatabaseConnectionException If a database connection error occurs.
	 * @throws AdminNotFoundException      If the admin is not found.
	 */
	@Override
	public boolean updateAdmin(String username, int choice)
			throws SQLException, DatabaseConnectionException, AdminNotFoundException {

		adminDao = new AdminDao();
		String updateValue = null;
		boolean isUpdated = false;
		switch (choice) {

		case 1: {

			System.out.println("Enter the new email to update : ");
			updateValue = in.next();
			isUpdated = adminDao.updateAdmin(username, "email", updateValue);
			break;
		}
		case 2: {

			System.out.println("Enter the new phone number for updation");
			updateValue = in.next();
			isUpdated = adminDao.updateAdmin(username, "phoneNumber", updateValue);
			break;
		}
		case 3: {

			System.out.println("Enter the new password");
			updateValue = in.next();
			isUpdated = adminDao.updateAdmin(username, "password", updateValue);
			break;
		}

		}

		return isUpdated;
	}

	/**
	 * Deletes an admin by ID.
	 *
	 * @param adminId The ID of the admin to delete.
	 */
	@Override
	public void deleteAdmin(int adminId) {

		adminDao = new AdminDao();
		try {
			boolean isDeleted = adminDao.deleteAdmin(adminId);
			if (isDeleted) {
				System.out.println("Admin with id " + adminId + " deleted successfully!!");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		} catch (AdminNotFoundException e) {

			e.printStackTrace();
		}

	}

}
