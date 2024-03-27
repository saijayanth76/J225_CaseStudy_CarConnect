package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hexaware.enums.Status;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.ReservationException;
import com.hexaware.exception.VehicleNotFoundException;
import com.hexaware.model.Customer;
import com.hexaware.model.Reservation;
import com.hexaware.model.Vehicle;
import com.hexaware.util.DBConnUtil;

/**
 * This class provides methods to interact with the Reservation database table.
 */
public class ReservationDao {

	/**
	 * Retrieves a reservation by its ID from the database.
	 *
	 * @param reservationId The ID of the reservation to retrieve.
	 * @return The reservation object if found.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws ReservationException        If the reservation with the given ID is
	 *                                     not found.
	 */
	public Reservation getReservationById(int reservationId)
			throws SQLException, DatabaseConnectionException, ReservationException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from reservation where reservationId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, reservationId);
		ResultSet result = ps.executeQuery();

		if (result.next()) {

			int reservation_id = result.getInt("reservationId");
			int customerId = result.getInt("customerId");
			int vehicleId = result.getInt("vehicleId");
			LocalDate startDate = result.getDate("startDate").toLocalDate();
			LocalDate endDate = result.getDate("endDate").toLocalDate();
			Double totalCost = result.getDouble("totalCost");
			Status status = Status.valueOf(result.getString("status").toUpperCase());

			CustomerDao custDao = new CustomerDao();
			VehicleDao vdao = new VehicleDao();

			try {
				Customer customer = custDao.getCustomerById(customerId);
				Vehicle vehicle = vdao.getVehicleById(vehicleId);

				Reservation reservation = new Reservation(reservation_id, customer, vehicle, startDate, endDate,
						totalCost, status);

				return reservation;

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DatabaseConnectionException e) {
				e.printStackTrace();
			} catch (CustomerNotFoundException e) {
				e.printStackTrace();
			} catch (VehicleNotFoundException e) {
				e.printStackTrace();
			}

		}

		DBConnUtil.dbClose();
		throw new ReservationException("Invalid Reservation Id");
	}

	/**
	 * Retrieves a list of reservations by customer ID from the database.
	 *
	 * @param customerId The ID of the customer whose reservations to retrieve.
	 * @return A list of reservation objects for the customer.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws CustomerNotFoundException   If the customer with the given ID is not
	 *                                     found.
	 */
	public List<Reservation> getReservationsByCustomerId(int customerId)
			throws SQLException, DatabaseConnectionException, CustomerNotFoundException {

		List<Reservation> reservationList;

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from reservation where customerId = ? ";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, customerId);
		ResultSet result = ps.executeQuery();

		reservationList = new ArrayList<>();

		while (result.next()) {

			int reservation_id = result.getInt("reservationId");
			int vehicleId = result.getInt("vehicleId");
			LocalDate startDate = result.getDate("startDate").toLocalDate();
			LocalDate endDate = result.getDate("endDate").toLocalDate();
			Double totalCost = result.getDouble("totalCost");
			Status status = Status.valueOf(result.getString("status").toUpperCase());

			CustomerDao custDao = new CustomerDao();
			VehicleDao vdao = new VehicleDao();

			try {
				Customer customer = custDao.getCustomerById(customerId);
				Vehicle vehicle = vdao.getVehicleById(vehicleId);

				Reservation reservation = new Reservation(reservation_id, customer, vehicle, startDate, endDate,
						totalCost, status);

				reservationList.add(reservation);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DatabaseConnectionException e) {
				e.printStackTrace();
			} catch (CustomerNotFoundException e) {
				e.printStackTrace();
			} catch (VehicleNotFoundException e) {
				e.printStackTrace();
			}

		}

		DBConnUtil.dbClose();
		return reservationList;

	}

	/**
	 * Creates a new reservation in the database.
	 *
	 * @param reservation The reservation object to create.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public void createReservation(Reservation reservation) throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "INSERT INTO Reservation (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setInt(1, reservation.getCustomerId());
		stmt.setInt(2, reservation.getVehicleId());
		stmt.setDate(3, Date.valueOf(reservation.getStartDate()));
		stmt.setDate(4, Date.valueOf(reservation.getEndDate()));
		stmt.setDouble(5, reservation.getTotalCost());
		stmt.setString(6, reservation.getStatus().name());

		stmt.executeUpdate();
		DBConnUtil.dbClose();

	}

	/**
	 * Updates the status of a reservation in the database.
	 *
	 * @param reservationId The ID of the reservation to update.
	 * @return True if the update is successful, false otherwise.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws ReservationException        If the reservation with the given ID is
	 *                                     not found.
	 */
	public boolean updateReservation(int reservationId)
			throws SQLException, DatabaseConnectionException, ReservationException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "update Reservation set status = ? where reservationId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, "CONFIRMED");
		ps.setInt(2, reservationId);

		int rowsAffected = ps.executeUpdate();

		DBConnUtil.dbClose();
		if (rowsAffected == 1) {
			return true;
		}

		throw new ReservationException("Invalid Reservation Id");

	}

	/**
	 * Cancels a reservation in the database.
	 *
	 * @param reservationId The ID of the reservation to cancel.
	 * @return True if the cancellation is successful, false otherwise.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 * @throws ReservationException        If the reservation with the given ID is
	 *                                     not found.
	 */
	public boolean CancelReservation(int reservationId)
			throws SQLException, DatabaseConnectionException, ReservationException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "delete from Reservation where reservationId = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, reservationId);

		if (ps.executeUpdate() == 1) {
			return true;
		}

		throw new ReservationException("Invalid Reservation Id");
	}

	/**
	 * Retrieves a list of all reservations from the database.
	 *
	 * @return A list of all reservation objects.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public List<Reservation> getAllReservationDetails() throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select * from reservation";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		List<Reservation> reservationsList = new ArrayList<>();

		while (rs.next()) {

			int reservation_id = rs.getInt("reservationId");
			int vehicleId = rs.getInt("vehicleId");
			int customerId = rs.getInt("customerId");
			LocalDate startDate = rs.getDate("startDate").toLocalDate();
			LocalDate endDate = rs.getDate("endDate").toLocalDate();
			Double totalCost = rs.getDouble("totalCost");
			Status status = Status.valueOf(rs.getString("status").toUpperCase());

			CustomerDao custDao = new CustomerDao();
			VehicleDao vdao = new VehicleDao();

			try {
				Customer customer = custDao.getCustomerById(customerId);
				Vehicle vehicle = vdao.getVehicleById(vehicleId);

				Reservation reservation = new Reservation(reservation_id, customer, vehicle, startDate, endDate,
						totalCost, status);

				reservationsList.add(reservation);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DatabaseConnectionException e) {
				e.printStackTrace();
			} catch (CustomerNotFoundException e) {
				e.printStackTrace();
			} catch (VehicleNotFoundException e) {
				e.printStackTrace();
			}

		}
		DBConnUtil.dbClose();
		return reservationsList;

	}

	/**
	 * Retrieves the vehicle utilization statistics from the database.
	 *
	 * @return A map of vehicle IDs to their respective utilization counts.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public Map<Integer, Integer> getVehicleUtilization() throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select vehicle.vehicleId, count(reservation.vehicleId) from reservation right join vehicle"
				+ " on vehicle.vehicleId = reservation.vehicleId group by vehicleId;";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		Map<Integer, Integer> map = new HashMap<>();

		while (rs.next()) {

			int vehicleId = rs.getInt(1);
			int utilization = rs.getInt(2);

			map.put(vehicleId, utilization);
		}

		DBConnUtil.dbClose();
		return map;
	}

	/**
	 * Retrieves the total revenue generated by each vehicle from the database.
	 *
	 * @return A map of vehicle IDs to their respective total revenue amounts.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public Map<Integer, Double> getTotalRevenueByVehicle() throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select vehicle.vehicleId, sum(reservation.totalCost) from reservation right join vehicle"
				+ " on vehicle.vehicleId = reservation.vehicleId group by vehicleId;";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		Map<Integer, Double> map = new HashMap<>();

		while (rs.next()) {

			int vehicleId = rs.getInt(1);
			double totalCost = rs.getDouble(2);

			map.put(vehicleId, totalCost);
		}

		DBConnUtil.dbClose();
		return map;
	}

	/**
	 * Retrieves the total revenue generated by each customer from the database.
	 *
	 * @return A map of customer IDs to their respective total revenue amounts.
	 * @throws SQLException                If a database access error occurs.
	 * @throws DatabaseConnectionException If there is an issue with the database
	 *                                     connection.
	 */
	public Map<Integer, Double> getTotalRevenueByCustomer() throws SQLException, DatabaseConnectionException {

		Connection conn = DBConnUtil.getDBConn();

		String query = "select Customer.customerId, sum(reservation.totalCost) from reservation right join Customer"
				+ " on Customer.customerId = reservation.customerId group by customerId;";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		Map<Integer, Double> map = new LinkedHashMap<>();

		while (rs.next()) {

			int customerId = rs.getInt(1);
			double totalCost = rs.getDouble(2);

			map.put(customerId, totalCost);
		}

		DBConnUtil.dbClose();
		return map;
	}

}
