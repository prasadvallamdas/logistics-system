package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Loading;

@Repository
public interface LoadingRepository extends JpaRepository<Loading, Integer> {

}
