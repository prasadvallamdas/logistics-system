package com.alpha.logisticsproject.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alpha.logisticsproject.entity.Order;
import com.alpha.logisticsproject.repository.OrderRepository;

@Repository
public class OrderDao {
	@Autowired
	private OrderRepository orderRepository;

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public Optional<Order> findOrderById(int id) {
		return orderRepository.findById(id);
	}

}
