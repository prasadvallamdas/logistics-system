package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.DriverDao;
import com.alpha.logisticsproject.entity.Driver;
import com.alpha.logisticsproject.exception.DriverAlreadyPresent;

@Service
@Transactional
public class DriverService {

	@Autowired
	private DriverDao driverDao;

	public ResponseEntity<ResponseStructure<Driver>> save(Driver driver) {
		ResponseStructure<Driver> responsestructure = new ResponseStructure<Driver>();
		
		// Optimized: Only check if an explicit ID is sent
		if (driver.getId() != 0 && driverDao.findDriverById(driver).isPresent()) {
			throw new DriverAlreadyPresent("Driver with this id " + driver.getId() + " already exists");		
		} else {
			// Enforce default status for the dashboard tracking rules
			if (driver.getAvailability() == null) {
				driver.setAvailability("AVAILABLE");
			}
			Driver savedDriver = driverDao.saveDriver(driver);
			if (savedDriver != null) {
				responsestructure.setStatus(HttpStatus.ACCEPTED.value());
				responsestructure.setMessage("Driver registered successfully with ID " + savedDriver.getId());
				responsestructure.setData(savedDriver);		
			}	
		}
		return new ResponseEntity<ResponseStructure<Driver>>(responsestructure, HttpStatus.ACCEPTED);
	}	
}