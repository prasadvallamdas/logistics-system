package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.Cargo;
import com.alpha.logisticsproject.service.CargoService;

@Controller
public class CargoController {
	@Autowired
	private CargoService cargoService;
	@PostMapping("/savecargo")
	public ResponseEntity<ResponseStructure< Cargo>> saveDriver(@RequestBody  Cargo cargo) {
		return  cargoService.save( cargo);
	}

}
