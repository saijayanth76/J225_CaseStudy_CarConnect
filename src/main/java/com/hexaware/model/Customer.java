package com.hexaware.model;

import java.time.LocalDate;

/**
 * Represents a customer entity with various attributes.
 */
public class Customer {

	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String userName;
	private String password;
	private LocalDate registrationdate;

	public Customer() {

	}

	public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber, String address,
			String userName, String password, LocalDate registrationdate) {

		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.registrationdate = registrationdate;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber, String address,
			String userName, String password, LocalDate registrationdate) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.registrationdate = registrationdate;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber, String address,
			String userName, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userName = userName;
		this.password = password;

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(LocalDate registrationdate) {
		this.registrationdate = registrationdate;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", userName=" + userName
				+ ", password=" + password + ", registrationdate=" + registrationdate + "]";
	}

}
