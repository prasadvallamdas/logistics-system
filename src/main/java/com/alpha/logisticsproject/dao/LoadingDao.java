package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alpha.logisticsproject.entity.Loading;
import com.alpha.logisticsproject.repository.LoadingRepository;

@Repository
public class LoadingDao {
	
	@Autowired
	private LoadingRepository loadingRepository;

	public Loading saveLoading(Loading loading) {
		return loadingRepository.save(loading);
	}
	
	public Optional<Loading> findLoadingById(Loading loading) {
		return loadingRepository.findById(loading.getId());
	}
	

}
