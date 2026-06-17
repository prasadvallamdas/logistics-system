package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.LoadingDao;
import com.alpha.logisticsproject.entity.Loading;
import com.alpha.logisticsproject.exception.LoadingAlreadyPresent;

@Service
@Transactional
public class LoadingService {
	
	@Autowired
	private LoadingDao loadingDao;
	
	public ResponseEntity<ResponseStructure<Loading>> save(Loading loading) {
		ResponseStructure<Loading> responsestructure = new ResponseStructure<Loading>();
		
		if (loading.getId() != 0 && loadingDao.findLoadingById(loading).isPresent()) {
			throw new LoadingAlreadyPresent("Loading details with this id " + loading.getId() + " already exists");		
		} else {	
			Loading savedloading = loadingDao.saveLoading(loading);
			if (savedloading != null) {
				responsestructure.setStatus(HttpStatus.ACCEPTED.value());
				responsestructure.setMessage("Loading point configuration saved under ID " + savedloading.getId());
				responsestructure.setData(savedloading);		
			}	
		}
		return new ResponseEntity<ResponseStructure<Loading>>(responsestructure, HttpStatus.ACCEPTED);	
	}	
}