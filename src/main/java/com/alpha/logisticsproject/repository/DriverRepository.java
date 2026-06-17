package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alpha.logisticsproject.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    
    // Enables the system to count drivers based on their current operational status
    long countByAvailability(String availability);
}