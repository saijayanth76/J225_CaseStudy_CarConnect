package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.controller.AuthenticationService;
import com.hexaware.exception.AuthenticationException;

/**
 * This class contains unit tests for customer authentication using the
 * AuthenticationService class.
 */
public class AuthenticationTest {

	/** 
	 * The AuthenticationService instance for testing. 
	 */
	AuthenticationService authService;

	/** 
	 * Set up method to initialize the AuthenticationService instance.
	 */
	@Before
	public void setUp() {

		authService = new AuthenticationService();
	}

	/**
	 *  Test case for customer authentication with invalid credentials. 
	 */
	@Test
	public void testCustomerAuthentication_InValidCredentials() {

		boolean isAuthenticated = false;
		try {
			isAuthenticated = authService.isCustomerExists("johndoe", "pass12");
		} catch (AuthenticationException e) {
			isAuthenticated = e.getValue();
		}

		assertFalse(isAuthenticated);
	}

	/**
	 *  Test case for customer authentication with valid credentials.
	 */
	@Test
	public void testCustomerAuthentication_ValidCredentials() {

		boolean isAuthenticated = false;
		try {
			isAuthenticated = authService.isCustomerExists("johndoe", "pass123");
		} catch (AuthenticationException e) {
			isAuthenticated = e.getValue();
		}

		assertTrue(isAuthenticated);
	}

	/** 
	 * Tear down method to clean up resources after testing. 
	 */
	@After
	public void tearDown() {

		authService = null;
	}
}
