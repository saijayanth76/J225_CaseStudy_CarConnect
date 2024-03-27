package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.hexaware.exception.AdminNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.Admin;
import com.hexaware.util.DBConnUtil;

/**
 * This class provides methods to interact with the Admin database table.
 */
public class AdminDao {

	/**
	 * Retrieves an Admin object by its unique ID.
	 *
	 * @param adminId The ID of the admin to retrieve.
	 * @return The Admin object corresponding to the given ID.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws AdminNotFoundException      If the admin with the provided ID is not
	 *                                     found in the database.
	 */
	public Admin getAdminById(int adminId) throws SQLException, DatabaseConnectionException, AdminNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from admin where adminId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, adminId);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			int admin_id = rs.getInt("adminId");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phonenumber = rs.getString("phoneNumber");
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			String role = rs.getString("role");
			LocalDate joinDate = rs.getDate("joinDate").toLocalDate();

			Admin admin = new Admin();

			admin.setAdminId(admin_id);
			admin.setFirstName(firstName);
			admin.setLastName(lastName);
			admin.setEmail(email);
			admin.setPhoneNumber(phonenumber);
			admin.setUserName(userName);
			admin.setPassword(password);
			admin.setRole(role);
			admin.setJoinDate(joinDate);

			return admin;
		}

		DBConnUtil.dbClose();
		throw new AdminNotFoundException("Invalid Admin Id");

	}

	/**
	 * Retrieves an Admin object by its username.
	 *
	 * @param username The username of the admin to retrieve.
	 * @return The Admin object corresponding to the given username.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws AdminNotFoundException      If the admin with the provided username
	 *                                     is not found in the database.
	 */
	public Admin getAdminByUsername(String username)
			throws SQLException, DatabaseConnectionException, AdminNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from admin where username = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			int admin_id = rs.getInt("adminId");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phonenumber = rs.getString("phoneNumber");
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			String role = rs.getString("role");
			LocalDate joinDate = rs.getDate("joinDate").toLocalDate();

			Admin admin = new Admin();

			admin.setAdminId(admin_id);
			admin.setFirstName(firstName);
			admin.setLastName(lastName);
			admin.setEmail(email);
			admin.setPhoneNumber(phonenumber);
			admin.setUserName(userName);
			admin.setPassword(password);
			admin.setRole(role);
			admin.setJoinDate(joinDate);

			return admin;
		}

		DBConnUtil.dbClose();
		throw new AdminNotFoundException("Admin with username" + " " + username + " " + "not found");

	}

	/**
	 * Registers a new admin in the system.
	 *
	 * @param admin The Admin object to register.
	 * @return True if the admin is successfully registered, false otherwise.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 */
	public boolean registerAdmin(Admin admin) throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "INSERT INTO Admin (FirstName, LastName, Email, PhoneNumber, Username, Password, Role, JoinDate)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, admin.getFirstName());
		ps.setString(2, admin.getLastName());
		ps.setString(3, admin.getEmail());
		ps.setString(4, admin.getPhoneNumber());
		ps.setString(5, admin.getRole());
		ps.setString(6, admin.getUserName());
		ps.setString(7, admin.getPassword());
		ps.setDate(8, Date.valueOf(LocalDate.now()));

		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();
		if (rowsAffected == 1) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the information of an existing admin.
	 *
	 * @param username    The username of the admin to update.
	 * @param updateField The field to update (e.g., firstName, lastName, email).
	 * @param updateValue The new value for the specified field.
	 * @return True if the admin information is successfully updated, false
	 *         otherwise.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws AdminNotFoundException      If the admin with the provided username
	 *                                     is not found in the database.
	 */
	public boolean updateAdmin(String username, String updateField, String updateValue)
			throws SQLException, DatabaseConnectionException, AdminNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "update Admin set updateField = ? where username = ?";
		StringBuilder builder = new StringBuilder(query);

		builder.replace(17, 28, updateField);
		PreparedStatement ps = conn.prepareStatement(builder.toString());

		ps.setString(1, updateValue);
		ps.setString(2, username);

		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();

		if (rowsAffected == 1) {
			return true;
		}

		throw new AdminNotFoundException("Invalid Admin id to do updation");

	}

	/**
	 * Deletes an admin from the system based on the provided admin ID.
	 *
	 * @param adminId The ID of the admin to delete.
	 * @return True if the admin is successfully deleted, false otherwise.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws AdminNotFoundException      If the admin with the provided ID is not
	 *                                     found in the database.
	 */
	public boolean deleteAdmin(int adminId) throws SQLException, DatabaseConnectionException, AdminNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "delete from admin where adminId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, adminId);
		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();
		if (rowsAffected == 1) {
			return true;
		}

		throw new AdminNotFoundException("Invalid Admin Id");
	}
}
