package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.VehicleNotFoundException;
import com.hexaware.model.Vehicle;
import com.hexaware.util.DBConnUtil;

/**
 * This class provides methods to interact with the Vehicle database table.
 */
public class VehicleDao {

	/**
	 * Retrieves a vehicle by its ID from the database.
	 *
	 * @param vehicleId The ID of the vehicle to retrieve.
	 * @return The vehicle object if found.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws VehicleNotFoundException    If the vehicle with the given ID is not
	 *                                     found.
	 */
	public Vehicle getVehicleById(int vehicleId)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from vehicle where vehicleId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, vehicleId);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			int vehicle_id = rs.getInt("vehicleId");
			String model = rs.getString("model");
			String make = rs.getString("make");
			int year = rs.getInt("year");
			String color = rs.getString("color");
			String regnum = rs.getString("registrationNumber");
			boolean availability = rs.getBoolean("availability");
			float dailyRate = rs.getFloat("dailyrate");

			Vehicle vehicle = new Vehicle();

			vehicle.setVehicleId(vehicle_id);
			vehicle.setModel(model);
			vehicle.setMake(make);
			vehicle.setYear(year);
			vehicle.setColor(color);
			vehicle.setRegistrationNumber(regnum);
			vehicle.setAvailability(availability);
			vehicle.setDailyRate(dailyRate);

			return vehicle;
		}

		DBConnUtil.dbClose();
		throw new VehicleNotFoundException("Invalid Vehicle Id");
	}

	/**
	 * Retrieves a list of available vehicles from the database.
	 *
	 * @return A list of available vehicle objects.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public List<Vehicle> getAvailableVehicles() throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from Vehicle where availability = true";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		List<Vehicle> availableVehicleList = new ArrayList<>();

		while (result.next()) {

			int vehicleId = result.getInt("vehicleId");
			String model = result.getString("model");
			String make = result.getString("make");
			int year = result.getInt("year");
			String color = result.getString("color");
			String regnum = result.getString("registrationNumber");
			boolean availability = result.getBoolean("availability");
			float dailyRate = result.getFloat("dailyrate");

			Vehicle vehicle = new Vehicle(vehicleId, model, make, year, color, regnum, availability, dailyRate);

			availableVehicleList.add(vehicle);

		}

		DBConnUtil.dbClose();
		return availableVehicleList;
	}

	/**
	 * Adds a new vehicle to the database.
	 *
	 * @param vehicle The vehicle object to add.
	 * @return True if the addition is successful, false otherwise.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public boolean addVehicle(Vehicle vehicle) throws DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "INSERT INTO Vehicle (Model, Make, Year, Color, RegistrationNumber, availability, DailyRate)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, vehicle.getModel());
			ps.setString(2, vehicle.getMake());
			ps.setInt(3, vehicle.getYear());
			ps.setString(4, vehicle.getColor());
			ps.setString(5, vehicle.getRegistrationNumber());
			ps.setBoolean(6, true);
			ps.setDouble(7, vehicle.getDailyRate());

			int rowsAffected = ps.executeUpdate();

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
	 * Updates a vehicle's information in the database.
	 *
	 * @param vehicleId   The ID of the vehicle to update.
	 * @param updateField The field to update (e.g., "dailyRate").
	 * @param updateValue The new value for the field.
	 * @return True if the update is successful, false otherwise.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws VehicleNotFoundException    If the vehicle with the given ID is not
	 *                                     found.
	 */
	public boolean updateVehicle(int vehicleId, String updateField, String updateValue)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "update Vehicle set updateField = ? where vehicleId = ?";
		StringBuilder builder = new StringBuilder(query);

		builder.replace(19, 30, updateField);
		PreparedStatement ps = conn.prepareStatement(builder.toString());

		if (updateField == "dailyRate") {
			ps.setDouble(1, Double.parseDouble(updateValue));
			ps.setInt(2, vehicleId);
		} else {
			ps.setString(1, updateValue);
			ps.setInt(2, vehicleId);
		}

		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();

		if (rowsAffected == 1) {
			return true;
		}

		throw new VehicleNotFoundException("Invalid Vehicle id to do updation");

	}

	/**
	 * Removes a vehicle from the database.
	 *
	 * @param vehicleId The ID of the vehicle to remove.
	 * @return True if the removal is successful, false otherwise.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws VehicleNotFoundException    If the vehicle with the given ID is not
	 *                                     found.
	 */
	public boolean removeVehicle(int vehicleId)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "delete from Vehicle where vehicleId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, vehicleId);

		if (ps.executeUpdate() == 1) {
			return true;
		}

		DBConnUtil.dbClose();
		throw new VehicleNotFoundException("Invalid Vehicle Id");
	}

	/**
	 * Fetches a list of all vehicles from the database.
	 *
	 * @return A list of all vehicle objects.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public List<Vehicle> fetchAllVehicles() throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from Vehicle";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		List<Vehicle> vehicleList = new ArrayList<>();

		while (result.next()) {

			int vehicleId = result.getInt("vehicleId");
			String model = result.getString("model");
			String make = result.getString("make");
			int year = result.getInt("year");
			String color = result.getString("color");
			String regnum = result.getString("registrationNumber");
			boolean availability = result.getBoolean("availability");
			float dailyRate = result.getFloat("dailyrate");

			Vehicle vehicle = new Vehicle(vehicleId, model, make, year, color, regnum, availability, dailyRate);

			vehicleList.add(vehicle);

		}

		DBConnUtil.dbClose();
		return vehicleList;
	}
}
