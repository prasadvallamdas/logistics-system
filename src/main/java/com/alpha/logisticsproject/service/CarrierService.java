package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.CarrierDao;
import com.alpha.logisticsproject.entity.Carrier;
import com.alpha.logisticsproject.exception.CarrierAlreadyPresent;

@Service
@Transactional
public class CarrierService {

	@Autowired
	private CarrierDao carrierDao;

	public ResponseEntity<ResponseStructure<Carrier>> save(Carrier carrier) {
		ResponseStructure<Carrier> responsestructure = new ResponseStructure<Carrier>();

		if (carrier.getId() != 0 && carrierDao.findCarrier(carrier.getId()).isPresent()) {
			throw new CarrierAlreadyPresent("Carrier with this id " + carrier.getId() + " already exists");
		} else {
			Carrier savedCarrier = carrierDao.saveCarrier(carrier);
			if (savedCarrier != null) {
				responsestructure.setStatus(HttpStatus.ACCEPTED.value());
				responsestructure.setMessage("Carrier saved successfully with ID " + savedCarrier.getId());
				responsestructure.setData(savedCarrier);
			}
		}
		return new ResponseEntity<ResponseStructure<Carrier>>(responsestructure, HttpStatus.ACCEPTED);
	}

	public Carrier getcarrierById(int id) {
		return carrierDao.findCarrier(id)
				.orElseThrow(() -> new RuntimeException("Carrier with Id " + id + " not found"));
	}
}