package com.alpha.logisticsproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.logisticsproject.entity.Address;
import com.alpha.logisticsproject.repository.AddressRepository;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepository addressRepository;
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	
	public Optional<Address> findAddressById(Address address) {
		return addressRepository.findById(address.getId());
	}

	public Optional<Address> findById(int id) {
		
		return addressRepository.findById(id);
	}
	
	public List<Address> findAll() {
	    return addressRepository.findAll();
	}

	public Address findAddressById(int loadingid) {
		return addressRepository.findById(loadingid).get();
	}


	

	

}
