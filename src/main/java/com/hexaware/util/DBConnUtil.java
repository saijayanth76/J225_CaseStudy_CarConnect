package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hexaware.exception.DatabaseConnectionException;

/**
 * This utility class provides methods for managing database connections.
 */
public class DBConnUtil {

	private static Connection conn;

	/**
	 * Retrieves a database connection.
	 *
	 * @return The database connection.
	 * @throws DatabaseConnectionException If there is an issue with establishing
	 *                                     the database connection.
	 */
	public static Connection getDBConn() throws DatabaseConnectionException {

		String connectionString = DBPropertyUtil.getConnectionString("db.properties");
		try {
			conn = DriverManager.getConnection(connectionString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn != null) {
			return conn;

		} else {
			throw new DatabaseConnectionException("Database Connection is not Established!!!");
		}
	}

	/**
	 * Closes the database connection.
	 *
	 * @throws SQLException If an SQL exception occurs while closing the connection.
	 */
	public static void dbClose() throws SQLException {
		conn.close();
	}
}
