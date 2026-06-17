package com.alpha.logisticsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.Address;
import com.alpha.logisticsproject.service.AddressService;

@Controller
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/saveaddress")
	public ResponseEntity<ResponseStructure<Address>> saveUser(@RequestBody Address address) {
		
		return addressService.save(address);
	}
	
	 @GetMapping("/getalladdress")
	    public ResponseEntity<List<Address>> getAllAddresses() {
	        return ResponseEntity.ok(addressService.getAllAddresses());
	    }


}
