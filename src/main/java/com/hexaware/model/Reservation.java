package com.hexaware.model;

import java.time.LocalDate;

import com.hexaware.enums.Status;

/**
 * Represents a Reservation entity with various attributes.
 */
public class Reservation {
	
	private int reservationId;
	private Customer customer; // has-a relationship
	private Vehicle vehicle;   // has-a relationship
	LocalDate startDate;
	LocalDate endDate;
	double totalCost;
	Status status;
	private int customerId;
	private int vehicleId;

	
	public Reservation() {
		
	}

	public Reservation(int reservationId, Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate,
			double totalCost, Status status) {
		super();
		this.reservationId = reservationId;
		this.customer = customer;
		this.vehicle = vehicle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
	}


	public Reservation(int customerId, int vehicleId, LocalDate startDate, LocalDate endDate, double totalCost,
			Status status) {
		
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
	}


	public int getReservationId() {
		return reservationId;
	}


	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", customer=" + customer + ", vehicle=" + vehicle
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", totalCost=" + totalCost + ", status="
				+ status + "]";
	}

}
