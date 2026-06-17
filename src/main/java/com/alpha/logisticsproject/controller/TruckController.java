package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.Truck;
import com.alpha.logisticsproject.service.TruckService;

@Controller
public class TruckController {
	@Autowired
	private TruckService truckService;
	@PostMapping("/savetruck")
	public ResponseEntity<ResponseStructure< Truck>> saveLoading(@RequestBody  Truck  truck) {
		return  truckService.save( truck);
	}

}
