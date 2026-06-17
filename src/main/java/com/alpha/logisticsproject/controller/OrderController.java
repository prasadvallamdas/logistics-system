package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dto.OrderDto;
import com.alpha.logisticsproject.entity.Order;
import com.alpha.logisticsproject.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
    public ResponseEntity<ResponseStructure<Order>> placeOrder(@RequestBody OrderDto orderDto) {
		System.out.println(orderDto);
        return orderService.saveOrder(orderDto);
    }
	
	 @PutMapping("/updateorder")
	    public ResponseEntity<ResponseStructure<Order>> updateOrder(@RequestParam int id, @RequestParam int carrierid,@RequestParam int unloadingdays) {
	        return orderService.updateOrder(id,carrierid,unloadingdays);
	    }
}
