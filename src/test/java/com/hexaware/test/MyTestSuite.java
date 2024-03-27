package com.hexaware.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthenticationTest.class, VehicleListTest.class, AvailableVehicleListTest.class,
		VehicleDetailsUpdateTest.class })

public class MyTestSuite {

}
