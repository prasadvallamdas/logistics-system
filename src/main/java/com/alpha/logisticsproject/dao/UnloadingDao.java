package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Unloading;
import com.alpha.logisticsproject.repository.UnloadingRepository;

@Repository
public class UnloadingDao {
	@Autowired
	private UnloadingRepository unloadingRepository;

	public Unloading saveUnloading(Unloading unloading) {
		return unloadingRepository.save(unloading);
	}
	
	public Optional<Unloading> findUnloadingById(Unloading loading) {
		return unloadingRepository.findById(loading.getId());
	}
	

}
