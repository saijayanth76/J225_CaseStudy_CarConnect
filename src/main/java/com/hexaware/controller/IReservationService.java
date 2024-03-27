package com.hexaware.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.ReservationException;
import com.hexaware.model.Reservation;
import com.hexaware.model.Vehicle;

public interface IReservationService {

	/**
	 * Creates a new reservation based on the provided information.
	 *
	 * @param availableVehicles The list of available vehicles.
	 * @param vehicleId         The ID of the selected vehicle.
	 * @param username          The username of the customer making the reservation.
	 * @param startDate         The start date of the reservation.
	 * @param endDate           The end date of the reservation.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 */
	public void createReservation(List<Vehicle> availablevehicles, int VehicleId, String username, LocalDate startDate,
			LocalDate endDate) throws SQLException, DatabaseConnectionException;

	/**
	 * Retrieves reservations based on the provided customer username.
	 *
	 * @param username The username of the customer.
	 * @return The list of reservations made by the customer.
	 */
	public List<Reservation> getReservationsByCustomerId(String username);

	/**
	 * Cancels a reservation based on the provided reservation ID.
	 *
	 * @param reservationId The ID of the reservation to be canceled.
	 * @throws SQLException                If an SQL exception occurs during the
	 *                                     process.
	 * @throws DatabaseConnectionException If a database connection exception occurs
	 *                                     during the process.
	 * @throws ReservationException        If an error occurs while canceling the
	 *                                     reservation.
	 */
	public void cancelReservation(int reservationId)
			throws SQLException, DatabaseConnectionException, ReservationException;

	/**
	 * Retrieves all reservation details.
	 *
	 * @return The list of all reservations in the system.
	 */
	public List<Reservation> getAllReservationDetails();

	/**
	 * Calculates and displays vehicle utilization statistics.
	 */
	public void getVehicleUtilization();

	/**
	 * Calculates and displays total revenue by vehicle.
	 */
	public void getTotalRevenueByVehicle();

	/**
	 * Calculates and displays total revenue by customer.
	 */
	public void getTotalRevenueByCustomer();

	/**
	 * Confirms a reservation based on the provided reservation ID.
	 *
	 * @param reservationId The ID of the reservation to be confirmed.
	 */
	public void confirmReservation(int reservationId);
}
