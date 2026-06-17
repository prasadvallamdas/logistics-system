package com.alpha.logisticsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alpha.logisticsproject.dto.DashboardDto;
import com.alpha.logisticsproject.enums.OrderStatus;
import com.alpha.logisticsproject.repository.DriverRepository;
import com.alpha.logisticsproject.repository.OrderRepository;
import com.alpha.logisticsproject.repository.TruckRepository;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private DriverRepository driverRepository;
    @Autowired private TruckRepository truckRepository;

    public DashboardDto getDashboardData() {
        DashboardDto dto = new DashboardDto();
        
        long totalOrders = orderRepository.count();
        long pendingOrders = orderRepository.countByStatus(OrderStatus.PENDING);

        dto.setTotalOrders(totalOrders);
        dto.setPendingOrders(pendingOrders);

        // --- Smart Market Demand Rule Engine Optimization ---
        if (pendingOrders == 0 && totalOrders > 0) {
            // Low Demand State: Display only actively deployed fleet units on the dashboard
            dto.setTotalDrivers(driverRepository.countByAvailability("BUSY"));
            dto.setTotalTrucks(truckRepository.countByStatus("BUSY"));
            dto.setAvailableDrivers(0);
            dto.setAvailableTrucks(0);
        } else {
            // Normal/High Demand State: Reflect the full operational asset pool
            dto.setTotalDrivers(driverRepository.count());
            dto.setTotalTrucks(truckRepository.count());
            dto.setAvailableDrivers(driverRepository.countByAvailability("AVAILABLE"));
            dto.setAvailableTrucks(truckRepository.countByStatus("AVAILABLE"));
        }
        return dto;
    }
}