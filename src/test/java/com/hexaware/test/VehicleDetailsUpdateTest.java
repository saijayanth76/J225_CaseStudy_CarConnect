package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.controller.VehicleService;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.VehicleNotFoundException;

/**
 * Unit tests for updating vehicle details using the VehicleService class.
 */
public class VehicleDetailsUpdateTest {
	
	/** 
	 * The VehicleService instance for testing. 
	 */
	VehicleService vehicleService;
	
	/** 
	 * Set up method to initialize the VehicleService instance.
	 */
	@Before
	public void setUp() {
	
		vehicleService= new VehicleService();
	}
	
	/** 
	 * Test case for updating vehicle daily rate with a valid vehicle id.
	 */
	@Test
	public void testUpdateVehicleDailyRate_ValidId() {
		
		try {
			
			assertTrue(vehicleService.updateVehicle(1, 300.0f));
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		} catch (VehicleNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/** 
	 * Test case for updating vehicle daily rate with an invalid vehicle id.
	 */
	@Test
	public void testUpdateVehicleDailyRate_InValidId() {
		
		try {
			
			assertFalse(vehicleService.updateVehicle(100, 600.0f));
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		} catch (VehicleNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/** 
	 * Tear down method to clean up resources after testing. 
	 */
	@After
	public void tearDown() {
		
		vehicleService =null;
	}

}
