package com.hexaware.controller;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.VehicleNotFoundException;
import com.hexaware.model.Vehicle;

public interface IVehicleService {

	/**
	 * Retrieves a vehicle based on the provided vehicle ID.
	 *
	 * @param vehicleId The ID of the vehicle to retrieve.
	 * @return The vehicle object corresponding to the ID.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws VehicleNotFoundException    If the requested vehicle is not found in
	 *                                     the system.
	 */
	public Vehicle getVehicleById(int vehicleId)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException;

	/**
	 * Retrieves the list of available vehicles.
	 *
	 * @return The list of available vehicles.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 */
	public List<Vehicle> getAvailableVehicles() throws SQLException, DatabaseConnectionException;

	/**
	 * Adds a new vehicle to the system.
	 *
	 * @param vehicle The vehicle object to add.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 */
	public void addVehicle(Vehicle vehicle) throws SQLException, DatabaseConnectionException;

	/**
	 * Updates the daily rate of a vehicle based on the provided vehicle ID.
	 *
	 * @param vehicleId The ID of the vehicle to update.
	 * @param dailyRate The new daily rate for the vehicle.
	 * @return True if the update is successful, false otherwise.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws VehicleNotFoundException    If the requested vehicle is not found in
	 *                                     the system.
	 */
	public boolean updateVehicle(int vehicleId, Float dailyRate)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException;

	/**
	 * Removes a vehicle from the system based on the provided vehicle ID.
	 *
	 * @param vehicleId The ID of the vehicle to remove.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws VehicleNotFoundException    If the requested vehicle is not found in
	 *                                     the system.
	 */
	public void removeVehicle(int vehicleId) throws SQLException, DatabaseConnectionException, VehicleNotFoundException;

	/**
	 * Fetches and displays all vehicles in the system.
	 */
	public void fetchAllVehicles();

}
