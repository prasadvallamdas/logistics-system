package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.Driver;
import com.alpha.logisticsproject.service.DriverService;

@Controller
public class DriverController {
	@Autowired
	private DriverService driverService;
	@PostMapping("/savedriver")
	public ResponseEntity<ResponseStructure< Driver>> saveDriver(@RequestBody  Driver driver) {
		return  driverService.save( driver);
	}

}
