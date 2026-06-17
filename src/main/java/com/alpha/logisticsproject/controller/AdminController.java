package com.alpha.logisticsproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.logisticsproject.entity.Order;
import com.alpha.logisticsproject.repository.OrderRepository;
import com.alpha.logisticsproject.service.OrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderService orderService;

    // Admin Monitoring Endpoint: Pulls every shipment across all users
    @GetMapping("/reports/all")
    public ResponseEntity<List<Order>> getMasterAuditLogs() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    // Dynamic Admin Override: Reassign fleet elements on the fly
    @PostMapping("/order/override")
    public ResponseEntity<Order> applyManifestOverride(@RequestParam int orderId, 
                                                       @RequestParam int driverId, 
                                                       @RequestParam int truckId) {
        return ResponseEntity.ok(orderService.adminOverrideOrder(orderId, driverId, truckId));
    }
}