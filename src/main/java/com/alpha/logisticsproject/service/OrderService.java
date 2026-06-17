package com.alpha.logisticsproject.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.dao.*;
import com.alpha.logisticsproject.dto.OrderDto;
import com.alpha.logisticsproject.entity.*;
import com.alpha.logisticsproject.enums.OrderStatus;
import com.alpha.logisticsproject.exception.AddressNotFoundException;
import com.alpha.logisticsproject.exception.OrderNotFoundException;
import com.alpha.logisticsproject.repository.DriverRepository;
import com.alpha.logisticsproject.repository.TruckRepository;

@Service
@Transactional
public class OrderService {

	@Autowired private OrderDao orderDao;
	@Autowired private CargoDao cargoDao;
	@Autowired private LoadingDao loadingDao;
	@Autowired private UnloadingDao unloadingDao;
	@Autowired private AddressDao addressDao;
	@Autowired private CarrierDao carrierDao;
	@Autowired private DriverRepository driverRepository;
	@Autowired private TruckRepository truckRepository;

	public ResponseEntity<ResponseStructure<Order>> saveOrder(OrderDto orderDto) {
		ResponseStructure<Order> responseStructure = new ResponseStructure<>();

		Order order = new Order();
		order.setDate(LocalDate.parse(orderDto.getOrderdate()));
		order.setStatus(OrderStatus.PENDING);
		order.setBookedByUsername(orderDto.getUsername());
		order.setDistanceKm(orderDto.getDistanceKm());
		
		// Cost directly calculated from travel distance metrics
		order.setCost(orderDto.getDistanceKm() * 12); 

		Cargo c = new Cargo(orderDto.getCargoid(), orderDto.getCargoName(), orderDto.getCargoDescription(),
				orderDto.getCargowt(), orderDto.getCargocount());
		cargoDao.saveCargo(c);
		order.setCargo(c);

		Address loadAddr = addressDao.findAddressById(orderDto.getLoadingid());
		if (loadAddr == null) throw new AddressNotFoundException("Origin hub missing.");
		Loading loading = new Loading(LocalDate.now().toString(), "08:00", loadAddr);
		order.setLoadingadd(loadingDao.saveLoading(loading));

		Address unloadAddr = addressDao.findAddressById(orderDto.getUnloadingid());
		if (unloadAddr == null) throw new AddressNotFoundException("Destination hub missing.");
		Unloading unloading = new Unloading(LocalDate.now().toString(), "18:00", unloadAddr);
		order.setUnloadingadd(unloadingDao.saveUnloading(unloading));

		Order saved = orderDao.save(order);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Logistics order manifest generated successfully.");
		responseStructure.setData(saved);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Order>> updateOrder(int id, int carrierid, int unloadingdays) {
		Order order = orderDao.findOrderById(id)
				.orElseThrow(() -> new OrderNotFoundException("Manifest record not found."));

		ResponseStructure<Order> responseStructure = new ResponseStructure<>();

		if (order.getStatus() == OrderStatus.PENDING) {
			order.setStatus(OrderStatus.PLACED);
			order.setCarrier(carrierDao.findCarrier(carrierid).orElse(null));

			// Allocate operational driver asset
			Driver driver = driverRepository.findAll().stream()
					.filter(d -> "AVAILABLE".equalsIgnoreCase(d.getAvailability())).findFirst()
					.orElseThrow(() -> new RuntimeException("No drivers available."));
			driver.setAvailability("BUSY");
			driverRepository.save(driver);
			order.setDriver(driver);
			order.setDriverAssignmentStatus("ASSIGNED");

			// Allocate operational transport truck asset
			Truck truck = truckRepository.findAll().stream()
					.filter(t -> "AVAILABLE".equalsIgnoreCase(t.getStatus())).findFirst()
					.orElseThrow(() -> new RuntimeException("No trucks available."));
			truck.setStatus("BUSY");
			truckRepository.save(truck);
			order.setTruck(truck);
			order.setTruckAssignmentStatus("ASSIGNED");
			
			order.setLoadingStatus("COMPLETED");

		} else if (order.getStatus() == OrderStatus.PLACED) {
			order.setStatus(OrderStatus.COMPLETED);
			order.setUnloadingStatus("COMPLETED");
			order.setPaymentStatus("PAID");

			// Release assets back into the availability pool
			if (order.getDriver() != null) {
				order.getDriver().setAvailability("AVAILABLE");
				driverRepository.save(order.getDriver());
			}
			if (order.getTruck() != null) {
				order.getTruck().setStatus("AVAILABLE");
				truckRepository.save(order.getTruck());
			}
		}

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Workflow state transitioned smoothly.");
		responseStructure.setData(orderDao.save(order));
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	// Admin Override Capabilities: Allows modifying active orders based on fleet asset availability
	public Order adminOverrideOrder(int orderId, int newDriverId, int newTruckId) {
		Order order = orderDao.findOrderById(orderId).orElseThrow(() -> new RuntimeException("Order not found."));
		
		if(order.getDriver() != null) {
			order.getDriver().setAvailability("AVAILABLE");
			driverRepository.save(order.getDriver());
		}
		Driver newDriver = driverRepository.findById(newDriverId).orElseThrow(() -> new RuntimeException("Driver not found."));
		newDriver.setAvailability("BUSY");
		order.setDriver(driverRepository.save(newDriver));

		if(order.getTruck() != null) {
			order.getTruck().setStatus("AVAILABLE");
			truckRepository.save(order.getTruck());
		}
		Truck newTruck = truckRepository.findById(newTruckId).orElseThrow(() -> new RuntimeException("Truck not found."));
		newTruck.setStatus("BUSY");
		order.setTruck(truckRepository.save(newTruck));

		return orderDao.save(order);
	}
}