package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
