package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Carrier;
import com.alpha.logisticsproject.repository.CarrierRepository;

@Repository
public class CarrierDao {
	
	@Autowired
	private CarrierRepository carrierRepository;
	
	public Carrier saveCarrier(Carrier carrier) {
		return carrierRepository.save(carrier);
		
	}
	
	public Optional<Carrier> findCarrier(int id){
		return carrierRepository.findById(id);
	}

}
