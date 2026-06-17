package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.TruckDao;
import com.alpha.logisticsproject.entity.Truck;
import com.alpha.logisticsproject.exception.TruckAlreadyPresent;

@Service
@Transactional
public class TruckService {
	@Autowired
	private TruckDao truckDao;
   public ResponseEntity<ResponseStructure<Truck>> save(Truck  truck) {
		
		ResponseStructure<Truck> responsestructure=new ResponseStructure<Truck>();
		
		
		if( truckDao.findTruckById( truck).isPresent()) {
			throw new TruckAlreadyPresent("Truck with this id "+ truck.getId()+" already exists");		
		}
			
		else {
			
			Truck savedTruck =  truckDao.saveTruck( truck);
			 if(savedTruck!=null) {
			responsestructure.setStatus(HttpStatus.ACCEPTED.value());
			responsestructure.setMessage("Truck with this id "+ truck.getId()+" saved succesfully");
			responsestructure.setData(savedTruck);		
			 }	
		}
		return new ResponseEntity<ResponseStructure<Truck>>(responsestructure,HttpStatus.ACCEPTED);
			
			
		}	

}