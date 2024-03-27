package com.hexaware.model;

import java.time.LocalDate;

/**
 * Represents an Admin in the system with various attributes such as adminId,
 * firstName, lastName, email, phoneNumber, userName, password, role, and
 * joinDate.
 */

public class Admin {

	private int adminId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String userName;
	private String password;
	private String role;
	private LocalDate joinDate;

	/** Default constructor for the Admin class. */
	public Admin() {

	}

	/**
	 * Parameterized constructor for the Admin class.
	 * 
	 * @param adminId     The unique identifier for the admin.
	 * @param firstName   The first name of the admin.
	 * @param lastName    The last name of the admin.
	 * @param email       The email address of the admin.
	 * @param phoneNumber The phone number of the admin.
	 * @param userName    The username of the admin.
	 * @param password    The password of the admin.
	 * @param role        The role of the admin.
	 * @param joinDate    The join date of the admin.
	 */

	public Admin(int adminId, String firstName, String lastName, String email, String phoneNumber, String userName,
			String password, String role, LocalDate joinDate) {

		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.joinDate = joinDate;
	}

	/**
	 * OverLoaded Constructor for the Admin class excluding the adminId.
	 * 
	 * @param firstName   The first name of the admin.
	 * @param lastName    The last name of the admin.
	 * @param email       The email address of the admin.
	 * @param phoneNumber The phone number of the admin.
	 * @param userName    The username of the admin.
	 * @param password    The password of the admin.
	 * @param role        The role of the admin.
	 * @param joinDate    The join date of the admin.
	 */
	public Admin(String firstName, String lastName, String email, String phoneNumber, String userName, String password,
			String role, LocalDate joinDate) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.joinDate = joinDate;
	}

	/**
	 * Get the admin's unique identifier.
	 * 
	 * @return The adminId.
	 */
	public int getAdminId() {
		return adminId;
	}

	/**
	 * Set the admin's unique identifier.
	 * 
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * Get the admin's first name.
	 * 
	 * @return The firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the admin's first name.
	 * 
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the admin's last name.
	 * 
	 * @return The lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the admin's last name.
	 * 
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the admin's email address.
	 * 
	 * @return The email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the admin's email address.
	 * 
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the admin's phone number.
	 * 
	 * @return The phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the admin's phone number.
	 * 
	 * @param phonenumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}

	/**
	 * Get the admin's username.
	 * 
	 * @return The userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the admin's username.
	 * 
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the admin's password.
	 * 
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the admin's password.
	 * 
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the admin's role.
	 * 
	 * @return The role.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Set the admin's role.
	 * 
	 * @param role The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Get the admin's join date.
	 * 
	 * @return The joinDate.
	 */
	public LocalDate getJoinDate() {
		return joinDate;
	}

	/**
	 * Set the admin's join date.
	 * 
	 * @param joinDate The joinDate to set.
	 */
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", userName=" + userName + ", password=" + password + ", role="
				+ role + ", joinDate=" + joinDate + "]";
	}

}
