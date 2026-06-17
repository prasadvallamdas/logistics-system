package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.Loading;
import com.alpha.logisticsproject.service.LoadingService;

@Controller
public class LoadingController {
	
	@Autowired
	private LoadingService loadingService;
	
	@PostMapping("/saveloading")
	public ResponseEntity<ResponseStructure< Loading>> saveLoading(@RequestBody  Loading  loading) {
		return  loadingService.save( loading);
	}

}
