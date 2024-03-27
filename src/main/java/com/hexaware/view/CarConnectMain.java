package com.hexaware.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.hexaware.controller.AdminService;
import com.hexaware.controller.AuthenticationService;
import com.hexaware.controller.CustomerService;
import com.hexaware.controller.IAdminService;
import com.hexaware.controller.IAuthenticationService;
import com.hexaware.controller.ICustomerService;
import com.hexaware.controller.IReservationService;
import com.hexaware.controller.IVehicleService;
import com.hexaware.controller.ReservationService;
import com.hexaware.controller.VehicleService;
import com.hexaware.exception.AdminNotFoundException;
import com.hexaware.exception.AuthenticationException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.InvalidInputException;
import com.hexaware.exception.ReservationException;
import com.hexaware.exception.VehicleNotFoundException;
import com.hexaware.model.Admin;
import com.hexaware.model.Customer;
import com.hexaware.model.Reservation;
import com.hexaware.model.Vehicle;

/**
 * This class contains the main method to run the CarConnect application.
 */
public class CarConnectMain {

	public static void main(String[] args) {

		IAuthenticationService authService = new AuthenticationService();
		ICustomerService custService = new CustomerService();
		IVehicleService vehicleService = new VehicleService();
		IReservationService reservationService = new ReservationService();
		IAdminService adminService = new AdminService();

		System.out.println();
		System.out.println("***********************************************************************");
		System.out.println("**                                                                   **");
		System.out.println("**        Welcome to CarConnect, a Car Rental Platform               **");
		System.out.println("**                                                                   **");
		System.out.println("***********************************************************************");
		System.out.println();

		Scanner scan = new Scanner(System.in);

		System.out.println("press 1 to Login as Customer");
		System.out.println("press 2 to Login as Admin");
		int loginType = scan.nextInt();

		if (loginType == 1) {

			System.out.println("Enter the username :");
			String username = scan.next();
			System.out.println("Enter the password :");
			String password = scan.next();
			boolean exists = false;
			try {
				exists = authService.isCustomerExists(username, password);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}

			if (exists) {

				String ch = null;

				do {
					System.out.println("Enter your choice:");
					System.out.println("1. Update Details of " + username);
					System.out.println("2. Get all Available Vehicles for Reservation");
					System.out.println("3. Vehicle Reservation");
					System.out.println("4. View all Reservations made by " + username);
					System.out.println("5. View Completed Reservations of " + username);
					System.out.println("6. Cancel Reservation");

					int choice = scan.nextInt();
					switch (choice) {
					case 1: {

						System.out.println("Available options for Updation");
						System.out.println("---------------------------------");
						System.out.println("1. Change Email");
						System.out.println("2. Update Phone Number");
						System.out.println("3. Update Address");
						System.out.println("4. Change Password");

						int choiceOpted = scan.nextInt();
						boolean isUpdated = false;

						try {
							isUpdated = custService.updateCustomer(username, choiceOpted);
							if (isUpdated) {
								System.out.println("Updated Successfully!!!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						} catch (CustomerNotFoundException e) {
							e.printStackTrace();
						}
						break;

					}
					case 2: {

						System.out.println("All Available Vehicles Data");
						try {
							List<Vehicle> availableVehiclesList = vehicleService.getAvailableVehicles();

							Iterator<Vehicle> it = availableVehiclesList.iterator();

							while (it.hasNext()) {
								System.out.println(it.next());
							}

						} catch (SQLException e) {

							e.printStackTrace();
						} catch (DatabaseConnectionException e) {

							e.printStackTrace();
						}
						break;
					}
					case 3: {

						List<Vehicle> availableVehiclesList = new ArrayList<>();

						try {

							availableVehiclesList = vehicleService.getAvailableVehicles();
							Iterator<Vehicle> it = availableVehiclesList.iterator();

							while (it.hasNext()) {
								System.out.println(it.next());
							}

							System.out.println(
									"Enter the Vehicle Id from the above list for which you want to do Reservation : ");
							int VehicleId = scan.nextInt();

							System.out.println("Enter from which date you want to do reservation(YYYY-MM-DD) : ");
							LocalDate startDate = Date.valueOf(scan.next()).toLocalDate();

							System.out.println("Enter end date of the reservation(YYYY-MM-DD) : ");
							LocalDate endDate = Date.valueOf(scan.next()).toLocalDate();

							reservationService.createReservation(availableVehiclesList, VehicleId, username, startDate,
									endDate);

						} catch (SQLException | DatabaseConnectionException e) {
							e.printStackTrace();
						}

						break;
					}
					case 4: {

						System.out.println("Details of all Bookings");
						System.out.println("-----------------------------");
						List<Reservation> reservations = reservationService.getReservationsByCustomerId(username);

						Iterator<Reservation> it = reservations.iterator();

						while (it.hasNext()) {
							System.out.println(it.next());
						}

						break;
					}
					case 5: {

						System.out.println("Details of Completed Reservations");
						System.out.println("-----------------------------------");
						List<Reservation> reservations = reservationService.getReservationsByCustomerId(username);

						
						short count = 0;
						for(Reservation reservation: reservations) {
							if(reservation.getStatus().name().equalsIgnoreCase("COMPLETED")) {
								System.out.println(reservation);
								count++;
							}
						}
						if (count == 0) {
							System.out.println("No Data Found for Completed Reservations....");
						}

						break;
					}
					case 6: {
						List<Reservation> reservations = reservationService.getReservationsByCustomerId(username);

						Iterator<Reservation> it = reservations.iterator();

						while (it.hasNext()) {
							System.out.println(it.next());
						}
						System.out.println("Enter the Reservation id of reservation from above list to cancel :");
						int reservationId = scan.nextInt();
						try {
							reservationService.cancelReservation(reservationId);
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						} catch (ReservationException e) {
							e.printStackTrace();
						}

						break;
					}
					default: {
						System.out.println("Enter the right choice. ");
					}
					}
					System.out.println("Do you want to continue? Press(Y/y)");
					ch = scan.next();
				} while (ch.equals("Y") || ch.equals("y"));
				System.out.println("Thanks for using our system !!! Exiting...");
				System.exit(0);
			}
		} else if (loginType == 2) {

			System.out.println("Enter the username :");
			String username = scan.next();
			System.out.println("Enter the password :");
			String password = scan.next();
			boolean exists = false;
			try {
				exists = authService.isAdminExists(username, password);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}

			if (exists) {
				String ch = null;

				do {
					System.out.println("Enter your choice:");

					System.out.println("-----------MANAGE VEHICLES---------\n");

					System.out.println("1. Add a new Vehicle ");
					System.out.println("2. View all Vehicles ");
					System.out.println("3. View all Avaialble Vehicles for Rent");
					System.out.println("4. Modify Vehicle's Daily Rate ");
					System.out.println("5. Remove a Vehicle ");
					System.out.println("6. Show a Vehicle's Details");

					System.out.println("\n-----------MANAGE CUSTOMERS---------\n");

					System.out.println("7. Add a new Customer ");
					System.out.println("8. Remove a Customer ");

					System.out.println("\n-----------MANAGE RESERVATIONS---------\n");

					System.out.println("9. Confirm Pending Reservations ");

					System.out.println("\n-----------MANAGE ADMIN---------\n");

					System.out.println("10. Add a new Admin ");
					System.out.println("11. View Admin by adminId ");
					System.out.println("12. Update Admin Details ");
					System.out.println("13. Delete Admin ");

					System.out.println("\n-----------REPORTS---------\n");

					System.out.println("14. View all Reservation Details ");
					System.out.println("15. View Vehicle Utilization ");
					System.out.println("16. Total Revenue made by Vehicle ");
					System.out.println("17. Total Revenue made by Customer ");
					int choice = scan.nextInt();
					switch (choice) {

					case 1: {

						Vehicle vehicle = new Vehicle();
						try {
							vehicleService.addVehicle(vehicle);
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						}
						break;
					}
					case 2: {

						vehicleService.fetchAllVehicles();
						break;
					}
					case 3: {

						System.out.println("All Available Vehicles Data");
						try {
							List<Vehicle> availableVehiclesList = vehicleService.getAvailableVehicles();

							Iterator<Vehicle> it = availableVehiclesList.iterator();
							while (it.hasNext()) {
								System.out.println(it.next());
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						}
						break;
					}
					case 4: {

						System.out.println("Enter the Vehicle id to do Updation: ");
						int vehicleId = scan.nextInt();
						System.out.println("Enter the updated Daily Rate: ");
						Float dailyRate = scan.nextFloat();
						try {
							boolean isUpdated = vehicleService.updateVehicle(vehicleId, dailyRate);
							if(isUpdated) {
								System.out.println("Vehicle Daily Rate Updated Successfully to "+dailyRate);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						} catch (VehicleNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
					case 5: {

						System.out.println("Enter the Vehicle Id you want to Remove");
						int vehicleId = scan.nextInt();
						try {
							vehicleService.removeVehicle(vehicleId);
						} catch (SQLException | DatabaseConnectionException | VehicleNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}

					case 6: {
						System.out.println("Enter the vehicleId: ");
						int vehicleId = scan.nextInt();
						try {
							Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
							System.out.println(vehicle);
						} catch (SQLException | DatabaseConnectionException | VehicleNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}

					case 7: {
						Customer customer = new Customer();
						try {
							boolean isCustomerRegistered = custService.registerCustomer(customer);
							if (isCustomerRegistered) {
								System.out.println("Customer Registered Successfully!!");
							}
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						}
						break;
					}
					case 8: {
						System.out.println("Enter the Customer id to delete: ");
						int customerId = scan.nextInt();
						boolean isDeleted;
						try {
							isDeleted = custService.deleteCustomer(customerId);
							if (isDeleted) {
								System.out.println("Customer with " + customerId + " deleted Successfully!!");
							}
						} catch (DatabaseConnectionException | SQLException | CustomerNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
					case 9: {

						List<Reservation> reservationsList = reservationService.getAllReservationDetails();

						for (Reservation reservation : reservationsList) {

							if (reservation.getStatus().name().equalsIgnoreCase("PENDING")) {
								System.out.println(reservation);
							}
						}
						System.out.println("Enter the reservation id to confirm from the above list: ");
						int reservationId = scan.nextInt();
						reservationService.confirmReservation(reservationId);
						break;
					}
					case 10: {
						Admin admin = new Admin();
						try {
							boolean isAdminRegistered = adminService.registerAdmin(admin);
							if (isAdminRegistered) {
								System.out.println("Admin Registered Successfully!!");
							}
						} catch (SQLException | DatabaseConnectionException | InvalidInputException e) {
							e.printStackTrace();
						}
						break;
					}
					case 11: {
						System.out.println("Enter the admin id: ");
						int adminId = scan.nextInt();
						try {
							Admin admin = adminService.getAdminById(adminId);
							System.out.println(admin);
						} catch (SQLException | DatabaseConnectionException | AdminNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
					case 12: {
						
						System.out.println("Available options for Updation");
						System.out.println("---------------------------------");
						System.out.println("1. Change Email");
						System.out.println("2. Update Phone Number");
						System.out.println("3. Change Password");
						
						int choiceOpted = scan.nextInt();
						boolean isUpdated = false;

						try {
							isUpdated = adminService.updateAdmin(username, choiceOpted);
							if (isUpdated) {
								System.out.println("Updated Successfully!!!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						} catch (AdminNotFoundException e) {
							e.printStackTrace();
						}
						break;
						
					}
					case 13: {
						System.out.println("Enter the admin id to delete");
						int adminId = scan.nextInt();

						try {
							adminService.deleteAdmin(adminId);
						} catch (SQLException | DatabaseConnectionException | AdminNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
					case 14: {
						List<Reservation> reservationsList = reservationService.getAllReservationDetails();

						Iterator<Reservation> it = reservationsList.iterator();

						while (it.hasNext()) {
							System.out.println(it.next());
						}
						break;
					}
					case 15: {
						reservationService.getVehicleUtilization();
						break;
					}
					case 16: {
						reservationService.getTotalRevenueByVehicle();
						break;
					}
					case 17: {
						reservationService.getTotalRevenueByCustomer();
						break;
					}

					default: {
						System.out.println("Enter the right choice. ");
					}
					}
					System.out.println("Do you want to continue? Press(Y/y)");
					ch = scan.next();
				} while (ch.equals("Y") || ch.equals("y"));
				System.out.println("Thanks for using our system !!! Exiting...");
				System.exit(0);
			}
		} else {
			System.out.println("Enter the right choice");
		}
		scan.close();
	}
}
