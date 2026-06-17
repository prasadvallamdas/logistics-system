package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.Unloading;
import com.alpha.logisticsproject.service.UnloadingService;

@Controller
public class UnloadingController {
	@Autowired
	private UnloadingService unloadingService;
	
	@PostMapping("/saveunloading")
	public ResponseEntity<ResponseStructure< Unloading>> saveLoading(@RequestBody  Unloading  unloading) {
		return  unloadingService.save( unloading);
	}

}
