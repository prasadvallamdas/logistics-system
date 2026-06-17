package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
