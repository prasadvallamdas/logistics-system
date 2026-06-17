package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Carrier;


@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer>{

}
