package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.dao.CustomerDao;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.Customer;

/**
 * Unit tests for updating customer information using the CustomerDao class.
 */
public class UpdateCustomerInformationTest {
	
	/** 
	 * The CustomerDao instance for testing. 
	 */
	CustomerDao custDao;
	
	/** 
	 * Set up method to initialize the CustomerDao instance.
	 */
	@Before
	public void setUp() {
	
		custDao= new CustomerDao();
	}
	
	/** 
	 * Test case for registering a customer with valid input.
	 */
	@Test
	public void testRegisterCustomerValidInput() {
		
		String firstName = "test";
		String lastName = "customer";
		String email = "test@gmail.com";
		String phoneNumber = "9876123400";
		String address = "Gandhi nagar";
		String username = "testUser";
		String password = "pass";
		
		Customer customer = new Customer(firstName, lastName, email, phoneNumber, address, username, password);
		
		try {
			
			assertTrue(custDao.registerCustomer(customer));
			
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Test case for registering a customer with invalid input. 
	 * 
	 */
	@Test
	public void testRegisterCustomerInvalidInput() {
		
		String firstName = "james";
		String lastName = "customer";
		String email = "james@gmail.com";
		String phoneNumber = "8989894566";
		String address = "visakhapatnam";
		String username = "testUser";
		String password = "pass1";
		
		Customer customer = new Customer(firstName, lastName, email, phoneNumber, address, username, password);
		
		try {
			
			assertFalse(custDao.registerCustomer(customer));
			
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Tear down method to clean up resources after testing.
	 */
	@After
	public void tearDown() {
		
		custDao =null;
	}

}
