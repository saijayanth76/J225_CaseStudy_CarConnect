package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.controller.VehicleService;
import com.hexaware.model.Vehicle;

/**
 * Unit tests for retrieving available vehicles using the VehicleService class.
 */
public class AvailableVehicleListTest {

	/** 
	 * The VehicleService instance for testing.
	 */
	VehicleService vehicleService;

	/** 
	 * Set up method to initialize the VehicleService instance.
	 */
	@Before
	public void setUp() {

		vehicleService = new VehicleService();
	}

	/** 
	 * Test case for retrieving available vehicles.
	 */
	@Test
	public void testGetAvailableVehicles() {

		List<Vehicle> availableList = vehicleService.getAvailableVehicles();

		assertNotNull(availableList);
		assertFalse(availableList.isEmpty());
	}

	/** 
	 * Tear down method to clean up resources after testing.
	 */
	@After
	public void tearDown() {

		vehicleService = null;
	}
}
