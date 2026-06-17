package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alpha.logisticsproject.entity.Truck;
import com.alpha.logisticsproject.repository.TruckRepository;

@Repository
public class TruckDao {
	@Autowired
	private TruckRepository truckRepository;

	public Truck saveTruck(Truck truck) {
		return truckRepository.save(truck);
	}
	
	public Optional<Truck> findTruckById(Truck truck) {
		return truckRepository.findById(truck.getId());
	}

	
	

}
