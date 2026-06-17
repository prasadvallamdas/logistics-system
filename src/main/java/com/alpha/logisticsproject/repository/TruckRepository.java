package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {

	long countByStatus(String status);
}