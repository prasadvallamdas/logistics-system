package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Cargo;
import com.alpha.logisticsproject.repository.CargoRepository;

@Repository
public class CargoDao {
	@Autowired
	private CargoRepository cargoRepository;
	
	public Cargo saveCargo(Cargo cargo) {
		return cargoRepository.save(cargo);
	}

	public Optional<Cargo> findCargoById(Cargo cargo) {
		return cargoRepository.findById(cargo.getId());
	}

	public Optional<Cargo> findCargoById(int cargoid) {
		
		return cargoRepository.findById(cargoid);
	}

}
