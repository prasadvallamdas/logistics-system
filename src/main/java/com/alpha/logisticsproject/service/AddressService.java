package com.alpha.logisticsproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.AddressDao;
import com.alpha.logisticsproject.entity.Address;
import com.alpha.logisticsproject.exception.AddressAlreadyPresent;

@Service
@Transactional
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> save(Address address){
		ResponseStructure<Address> responsestructure = new ResponseStructure<Address>();
		
		if (address.getId() != 0 && addressDao.findAddressById(address).isPresent()) {
			throw new AddressAlreadyPresent("Address with this id " + address.getId() + " already exists");		
		} else {	
			Address savedaddress = addressDao.saveAddress(address);
			if (savedaddress != null) {
				responsestructure.setStatus(HttpStatus.ACCEPTED.value());
				responsestructure.setMessage("Hub address database entry complete under ID " + savedaddress.getId());
				responsestructure.setData(savedaddress);		
			}	
		}
		return new ResponseEntity<ResponseStructure<Address>>(responsestructure, HttpStatus.ACCEPTED);	
	}	

	public List<Address> getAllAddresses() {
		return addressDao.findAll();
	}
}