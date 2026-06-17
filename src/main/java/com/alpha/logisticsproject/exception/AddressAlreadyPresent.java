package com.alpha.logisticsproject.exception;

public class AddressAlreadyPresent extends RuntimeException {
    // Clears the IDE serialization warning icon cleanly
    private static final long serialVersionUID = 1L;
	
    public AddressAlreadyPresent(String message) {
        super(message);
    }
}