package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.dao.VehicleDao;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.Vehicle;

/**
 * Unit tests for fetching all vehicles using the VehicleDao class.
 */
public class VehicleListTest {

	/** 
	 * The VehicleDao instance for testing.
	 */
	VehicleDao vehicleDao;

	/** 
	 * Set up method to initialize the VehicleDao instance.
	 */
	@Before
	public void setUp() {

		vehicleDao = new VehicleDao();
	}

	/** 
	 * Test case for fetching all vehicles from the database. 
	 */
	@Test
	public void testFetchAllVehicles() throws SQLException, DatabaseConnectionException {

		List<Vehicle> vehicleList = vehicleDao.fetchAllVehicles();

		assertNotNull(vehicleList);
		assertFalse(vehicleList.isEmpty());
	}

	/** 
	 * Tear down method to clean up resources after testing.
	 */
	@After
	public void tearDown() {

		vehicleDao = null;
	}

}
