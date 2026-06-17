package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.User;
import com.alpha.logisticsproject.repository.UserRepository;
@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findUserByIdr(User user) {
		return userRepository.findById(user.getId());
	}

	

}
