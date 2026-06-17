package com.alpha.logisticsproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long contact;
	private String availability = "AVAILABLE";
	
	@OneToOne
	private Truck truck;
	
	@OneToOne
	private Carrier carrier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Driver(int id, String name, long contact, String availability, Truck truck, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.availability = availability;
		this.truck = truck;
		this.carrier = carrier;
	}

	public Driver() {
	    super();
	}
	
	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", contact=" + contact + ", availability=" + availability
				+ ", truck=" + truck + ", carrier=" + carrier + "]";
	}
}