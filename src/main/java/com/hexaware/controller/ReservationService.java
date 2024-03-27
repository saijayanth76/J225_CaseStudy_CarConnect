package com.hexaware.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hexaware.dao.ReservationDao;
import com.hexaware.dao.VehicleDao;
import com.hexaware.enums.Status;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.ReservationException;
import com.hexaware.exception.VehicleNotFoundException;
import com.hexaware.model.Reservation;
import com.hexaware.model.Vehicle;

/**
 * This class implements the IReservationService interface and provides methods for reservation-related operations.
 */
public class ReservationService implements IReservationService {
	
	ReservationDao reservationDao;
	VehicleDao vehicleDao;
	Reservation reservation;
	ICustomerService custService;
	
	/**
     * Creates a reservation based on the specified parameters.
     *
     * @param availableVehicles The list of available vehicles.
     * @param vehicleId         The ID of the selected vehicle.
     * @param username          The username of the customer making the reservation.
     * @param startDate         The start date of the reservation.
     * @param endDate           The end date of the reservation.
     */
	@Override
	public void createReservation(List<Vehicle> availableVehicles, int vehicleId, String username, LocalDate startDate,
			LocalDate endDate) {
		
		int noOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
		
		double totalCost = 0;
		for(Vehicle vehicle : availableVehicles) {
			
			if(vehicle.getVehicleId() == vehicleId) {
				
				totalCost = noOfDays * vehicle.getDailyRate();
			}
		}
				
		custService = new CustomerService();
		int customerId = custService.getCustomerIdByUsername(username);
		reservation = new Reservation();
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setTotalCost(totalCost);
		reservation.setStatus(Status.PENDING);
		reservation.setCustomerId(customerId);
		reservation.setVehicleId(vehicleId);
		
		try {
			reservationDao = new ReservationDao();
			reservationDao.createReservation(reservation);
			
			System.out.println("Reservation Successfull !!");
			System.out.println("The total cost of the reservation is "+ totalCost);
			
			// setting availability of vehicle to false 
			vehicleDao = new VehicleDao();
			vehicleDao.updateVehicle(vehicleId, "availability", "0");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		} catch(VehicleNotFoundException e) {
			
			e.printStackTrace();
		}	

	}

	/**
     * Retrieves reservations made by a customer based on their username.
     *
     * @param username The username of the customer.
     * @return The list of reservations made by the customer.
     */
	@Override
	public List<Reservation> getReservationsByCustomerId(String username) {
		
		List<Reservation> reservationList = new ArrayList<>();
		reservationDao = new ReservationDao();
		custService = new CustomerService();
		int customerId = custService.getCustomerIdByUsername(username);
		try {
			reservationList = reservationDao.getReservationsByCustomerId(customerId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			
			e.printStackTrace();
		}
		return reservationList;
	}

	/**
	 * Cancels a reservation based on the given reservation ID.
	 *
	 * @param reservationId The ID of the reservation to be cancelled.
	 * @throws SQLException              If a SQL exception occurs while accessing the database.
	 * @throws DatabaseConnectionException If a database connection exception occurs.
	 * @throws ReservationException       If an error occurs while cancelling the reservation.
	 */
	@Override
	public void cancelReservation(int reservationId)
			throws SQLException, DatabaseConnectionException, ReservationException {
		reservationDao = new ReservationDao();
		
		Reservation reservation = reservationDao.getReservationById(reservationId);
		int vehicleId = reservation.getVehicle().getVehicleId();
		
		boolean isCancelled = reservationDao.CancelReservation(reservationId);
		if(isCancelled) {
			System.out.println("Reservation with id " + reservationId+ " Cancelled Successfully!!");
			vehicleDao = new VehicleDao();
			
			try {
				vehicleDao.updateVehicle(vehicleId, "availability", "1");
			} 
			 catch (VehicleNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Retrieves all reservation details.
	 *
	 * @return The list of all reservation details.
	 */
	@Override
	public List<Reservation> getAllReservationDetails() {
		
		reservationDao = new ReservationDao();
		List<Reservation> reservationsList = new ArrayList<>();
		try {
			
			reservationsList = reservationDao.getAllReservationDetails();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
		return reservationsList;
	}

	/**
	 * Retrieves vehicle utilization statistics.
	 */
	@Override
	public void getVehicleUtilization() {
		
		reservationDao = new ReservationDao();
		try {
			Map<Integer, Integer> vehicleUtilization = reservationDao.getVehicleUtilization();
			System.out.println("VehicleId\t Utilization");
			for(Map.Entry<Integer, Integer> utilization : vehicleUtilization.entrySet()) {
		
				System.out.println(utilization.getKey()+"\t\t "+utilization.getValue());
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves total revenue by vehicle.
	 */
	@Override
	public void getTotalRevenueByVehicle() {
		
		reservationDao = new ReservationDao();
		try {
			Map<Integer, Double> totalRevenue = reservationDao.getTotalRevenueByVehicle();
			System.out.println("VehicleId\t Total Revenue");
			for(Map.Entry<Integer, Double> revenue : totalRevenue.entrySet()) {
		
				System.out.println(revenue.getKey()+"\t\t "+revenue.getValue());
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * Retrieves total revenue by customer.
	 */
	@Override
	public void getTotalRevenueByCustomer() {
		
		reservationDao = new ReservationDao();
		try {
			Map<Integer, Double> totalRevenue = reservationDao.getTotalRevenueByCustomer();
			System.out.println("CustomerId\t Total Revenue");
			for(Map.Entry<Integer, Double> revenue : totalRevenue.entrySet()) {
		
				System.out.println(revenue.getKey()+"\t\t "+revenue.getValue());
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * Confirms a reservation based on the given reservation ID.
	 *
	 * @param reservationId The ID of the reservation to be confirmed.
	 */
	@Override
	public void confirmReservation(int reservationId) {
		
		reservationDao = new ReservationDao();
		try {
			boolean isConfirm = reservationDao.updateReservation(reservationId);
			if(isConfirm) {
				System.out.println("Reservation Confirmed!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
		
	}
}
