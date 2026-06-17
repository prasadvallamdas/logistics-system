package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Driver;
import com.alpha.logisticsproject.repository.DriverRepository;

@Repository
public class DriverDao {
	@Autowired
	private DriverRepository driverRepository;

	public Driver saveDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	public Optional<Driver> findDriverById(Driver driver) {
		return driverRepository.findById(driver.getId());
	}

}
