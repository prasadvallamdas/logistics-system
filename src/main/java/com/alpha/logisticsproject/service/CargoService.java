package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.CargoDao;
import com.alpha.logisticsproject.entity.Cargo;
import com.alpha.logisticsproject.exception.CargoAlreadyPresent;

@Service
@Transactional
public class CargoService {

	@Autowired
	private CargoDao cargoDao;

	public ResponseEntity<ResponseStructure<Cargo>> save(Cargo cargo) {
		ResponseStructure<Cargo> responsestructure = new ResponseStructure<Cargo>();
		
		if (cargo.getId() != 0 && cargoDao.findCargoById(cargo).isPresent()) {
			throw new CargoAlreadyPresent("Cargo with this id " + cargo.getId() + " already exists");		
		} else {
			Cargo savedCargo = cargoDao.saveCargo(cargo);
			if (savedCargo != null) {
				responsestructure.setStatus(HttpStatus.ACCEPTED.value());
				responsestructure.setMessage("Cargo specifications saved under ID " + savedCargo.getId());
				responsestructure.setData(savedCargo);		
			}	
		}
		return new ResponseEntity<ResponseStructure<Cargo>>(responsestructure, HttpStatus.ACCEPTED);
	}	
}