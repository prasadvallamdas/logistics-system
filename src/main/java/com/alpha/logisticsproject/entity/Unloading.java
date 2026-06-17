package com.alpha.logisticsproject.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Unloading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String date;
	private String time;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Unloading( String date, String time, Address address) {
		super();
		this.date = date;
		this.time = time;
		this.address = address;
	}

	public Unloading() {
		super();
	}

	@Override
	public String toString() {
		return "Unloading [id=" + id + ", date=" + date + ", time=" + time + ", address=" + address + "]";
	}
	
	

	
}
