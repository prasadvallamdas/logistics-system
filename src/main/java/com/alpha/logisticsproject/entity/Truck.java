package com.alpha.logisticsproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Truck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long number;
	private int capacity;
	private String status;
	
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

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Truck(int id, String name, long number, int capacity, String status, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.capacity = capacity;
		this.status = status;
		this.carrier = carrier;
	}

	public Truck() {
		super();
	}

	@Override
	public String toString() {
		return "Truck [id=" + id + ", name=" + name + ", number=" + number + ", capacity=" + capacity + ", status="
				+ status + ", carrier=" + carrier + "]";
	}
	
	
	
	

}
