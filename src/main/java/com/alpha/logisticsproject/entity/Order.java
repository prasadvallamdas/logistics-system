package com.alpha.logisticsproject.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.alpha.logisticsproject.enums.OrderStatus;

@Entity
@Table(name="LogisticOrder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int cost;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PENDING;
	private LocalDate date;
	
	@ManyToOne 
	private Carrier carrier;
	
	@OneToOne 
	private Loading loadingadd;
	
	@OneToOne 
	private Unloading unloadingadd;
	
	@OneToOne(cascade = CascadeType.ALL) 
	private Cargo cargo;
	
	@ManyToOne 
	private Driver driver;
	
	@ManyToOne 
	private Truck truck;

	// --- Enhanced Granular Real-Time Sub-states ---
	private String bookedByUsername; 
	private String truckAssignmentStatus = "PENDING";  // PENDING, ASSIGNED
	private String driverAssignmentStatus = "PENDING"; // PENDING, ASSIGNED
	private String loadingStatus = "PENDING";          // PENDING, IN_PROGRESS, COMPLETED
	private String unloadingStatus = "PENDING";        // PENDING, COMPLETED
	private int distanceKm;
	private String paymentStatus = "UNPAID";           // UNPAID, PAID

	// --- Getters and Setters ---
	public int getId() { 
		return id; 
	}
	
	public void setId(int id) { 
		this.id = id; 
	}
	
	public LocalDate getDate() { 
		return date; 
	}
	
	public void setDate(LocalDate date) { 
		this.date = date; 
	}
	
	public OrderStatus getStatus() { 
		return status; 
	}
	
	public void setStatus(OrderStatus status) { 
		this.status = status; 
	}
	
	public int getCost() { 
		return cost; 
	}
	
	public void setCost(int cost) { 
		this.cost = cost; 
	}
	
	public Carrier getCarrier() { 
		return carrier; 
	}
	
	public void setCarrier(Carrier carrier) { 
		this.carrier = carrier; 
	}
	
	public Loading getLoadingadd() { 
		return loadingadd; 
	}
	
	public void setLoadingadd(Loading loadingadd) { 
		this.loadingadd = loadingadd; 
	}
	
	public Unloading getUnloadingadd() { 
		return unloadingadd; 
	}
	
	public void setUnloadingadd(Unloading unloadingadd) { 
		this.unloadingadd = unloadingadd; 
	}
	
	public Cargo getCargo() { 
		return cargo; 
	}
	
	public void setCargo(Cargo cargo) { 
		this.cargo = cargo; 
	}
	
	public Driver getDriver() { 
		return driver; 
	}
	
	public void setDriver(Driver driver) { 
		this.driver = driver; 
	}
	
	public Truck getTruck() { 
		return truck; 
	}
	
	public void setTruck(Truck truck) { 
		this.truck = truck; 
	}
	
	public String getBookedByUsername() { 
		return bookedByUsername; 
	}
	
	public void setBookedByUsername(String bookedByUsername) { 
		this.bookedByUsername = bookedByUsername; 
	}
	
	public String getTruckAssignmentStatus() { 
		return truckAssignmentStatus; 
	}
	
	public void setTruckAssignmentStatus(String truckAssignmentStatus) {
		this.truckAssignmentStatus = truckAssignmentStatus; 
	}
	
	public String getDriverAssignmentStatus() { 
		return driverAssignmentStatus; 
	}
	
	public void setDriverAssignmentStatus(String driverAssignmentStatus) { 
		this.driverAssignmentStatus = driverAssignmentStatus; 
	}
	
	public String getLoadingStatus() { 
		return loadingStatus; 
	}
	
	public void setLoadingStatus(String loadingStatus) { 
		this.loadingStatus = loadingStatus; 
	}
	
	public String getUnloadingStatus() { 
		return unloadingStatus; 
	}
	
	public void setUnloadingStatus(String unloadingStatus) { 
		this.unloadingStatus = unloadingStatus; 
	}
	
	public int getDistanceKm() { 
		return distanceKm; 
	}
	
	public void setDistanceKm(int distanceKm) { 
		this.distanceKm = distanceKm; 
	}
	
	public String getPaymentStatus() { 
		return paymentStatus; 
	}
	
	public void setPaymentStatus(String paymentStatus) { 
		this.paymentStatus = paymentStatus; 
	}

	public Order() { 
		super(); 
	}
}