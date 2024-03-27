package com.hexaware.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.VehicleDao;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.VehicleNotFoundException;
import com.hexaware.model.Vehicle;

/**
 * Provides services related to vehicles such as retrieving available vehicles,
 * updating vehicle details, adding new vehicles, removing vehicles, and
 * fetching all vehicles.
 */
public class VehicleService implements IVehicleService {

	VehicleDao vehicleDao = new VehicleDao();
	Scanner in = new Scanner(System.in);

	/**
	 * Retrieves a list of available vehicles.
	 *
	 * @return The list of available vehicles.
	 */
	@Override
	public List<Vehicle> getAvailableVehicles() {

		List<Vehicle> availableVehiclesList = new ArrayList<>();
		try {
			availableVehiclesList = vehicleDao.getAvailableVehicles();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		}

		return availableVehiclesList;
	}

	/**
	 * Updates the daily rate of a vehicle.
	 *
	 * @param vehicleId The ID of the vehicle to be updated.
	 * @param dailyRate The new daily rate of the vehicle.
	 * @return True if the vehicle is updated successfully, false otherwise.
	 * @throws SQLException                If a SQL exception occurs while accessing
	 *                                     the database.
	 * @throws DatabaseConnectionException If a database connection exception
	 *                                     occurs.
	 * @throws VehicleNotFoundException    If the specified vehicle is not found.
	 */
	@Override
	public boolean updateVehicle(int vehicleId, Float dailyRate)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException {

		boolean isUpdated = vehicleDao.updateVehicle(vehicleId, "dailyRate", dailyRate.toString());

		if (isUpdated) {

			return true;
		}
		return false;

	}

	/**
	 * Retrieves a vehicle by its ID.
	 *
	 * @param vehicleId The ID of the vehicle to retrieve.
	 * @return The vehicle object corresponding to the given ID.
	 * @throws SQLException                If a SQL exception occurs while accessing
	 *                                     the database.
	 * @throws DatabaseConnectionException If a database connection exception
	 *                                     occurs.
	 * @throws VehicleNotFoundException    If the specified vehicle is not found.
	 */
	@Override
	public Vehicle getVehicleById(int vehicleId)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException {

		Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);
		return vehicle;
	}

	/**
	 * Adds a new vehicle to the system.
	 *
	 * @param vehicle The vehicle object containing the details to be added.
	 * @throws SQLException                If a SQL exception occurs while accessing
	 *                                     the database.
	 * @throws DatabaseConnectionException If a database connection exception
	 *                                     occurs.
	 */
	@Override
	public void addVehicle(Vehicle vehicle) throws SQLException, DatabaseConnectionException {

		System.out.println("Enter Vehicle Details");
		System.out.println("--------------------------");
		System.out.println("Enter the Vehicle Make: ");
		String make = in.next();
		System.out.println("Enter the Vehicle Model: ");
		String model = in.next();
		System.out.println("Enter the Year: ");
		int year = in.nextInt();
		System.out.println("Enter the Vehicle color: ");
		String color = in.next();
		System.out.println("Enter the Registration Number: ");
		String regnum = in.next();
		System.out.println("Enter the Daily Rate: ");
		Float dailyRate = in.nextFloat();

		vehicle.setMake(make);
		vehicle.setModel(model);
		vehicle.setYear(year);
		vehicle.setColor(color);
		vehicle.setRegistrationNumber(regnum);
		vehicle.setDailyRate(dailyRate);

		boolean isInserted = vehicleDao.addVehicle(vehicle);
		if (isInserted) {
			System.out.println("Vehicle Data added Successfully!!");
		} else {
			System.out.println("Problem araised while adding!");
		}

	}

	/**
	 * Removes a vehicle from the system based on its ID.
	 *
	 * @param vehicleId The ID of the vehicle to be removed.
	 * @throws SQLException                If a SQL exception occurs while accessing
	 *                                     the database.
	 * @throws DatabaseConnectionException If a database connection exception
	 *                                     occurs.
	 * @throws VehicleNotFoundException    If the specified vehicle is not found.
	 */
	@Override
	public void removeVehicle(int vehicleId)
			throws SQLException, DatabaseConnectionException, VehicleNotFoundException {

		boolean isRemoved = vehicleDao.removeVehicle(vehicleId);
		if (isRemoved) {
			System.out.println("Vehicle with " + vehicleId + " Removed Successfully!!");
		}

	}

	/**
	 * Fetches all vehicles from the system and displays them.
	 */
	@Override
	public void fetchAllVehicles() {

		try {

			List<Vehicle> vehicleList = vehicleDao.fetchAllVehicles();

			Iterator<Vehicle> it = vehicleList.iterator();

			while (it.hasNext()) {
				System.out.println(it.next());
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (DatabaseConnectionException e) {

			e.printStackTrace();
		}

	}
}
