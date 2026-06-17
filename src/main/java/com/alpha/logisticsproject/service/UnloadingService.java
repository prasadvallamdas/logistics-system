package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.UnloadingDao;
import com.alpha.logisticsproject.entity.Unloading;
import com.alpha.logisticsproject.exception.UnloadingAlreadyPresent;

@Service
@Transactional
public class UnloadingService {
	
	@Autowired
	private UnloadingDao unloadingDao;
	
	public ResponseEntity<ResponseStructure<Unloading>> save(Unloading unloading) {
		ResponseStructure<Unloading> responsestructure = new ResponseStructure<Unloading>();
		
		if (unloading.getId() != 0 && unloadingDao.findUnloadingById(unloading).isPresent()) {
			throw new UnloadingAlreadyPresent("Unloading details with this id " + unloading.getId() + " already exists");		
		} else {	
			Unloading savedunloading = unloadingDao.saveUnloading(unloading);
			if (savedunloading != null) {
				responsestructure.setStatus(HttpStatus.ACCEPTED.value());
				responsestructure.setMessage("Unloading point configuration saved under ID " + savedunloading.getId());
				responsestructure.setData(savedunloading);		
			}	
		}
		return new ResponseEntity<ResponseStructure<Unloading>>(responsestructure, HttpStatus.ACCEPTED);	
	}	
}