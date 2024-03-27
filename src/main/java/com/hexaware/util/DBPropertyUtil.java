package com.hexaware.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This utility class provides methods to retrieve database connection strings
 * from property files.
 */
public class DBPropertyUtil {

	/**
	 * Retrieves the database connection string based on the specified property
	 * file.
	 *
	 * @param propertyFileName The name of the property file containing database
	 *                         connection information.
	 * @return The database connection string.
	 */
	public static String getConnectionString(String propertyFileName) {

		Properties properties = new Properties();
		FileInputStream fileInputStream;

		try {

			fileInputStream = new FileInputStream("src//main//resources//" + propertyFileName);
			properties.load(fileInputStream);
			fileInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String serverName = properties.getProperty("serverName");
		String databaseName = properties.getProperty("databaseName");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		return String.format("jdbc:mysql://%s/%s?user=%s&password=%s", serverName, databaseName, username, password);
	}

}
