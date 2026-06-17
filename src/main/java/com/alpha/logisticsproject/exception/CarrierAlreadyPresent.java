package com.alpha.logisticsproject.exception;

public class CarrierAlreadyPresent extends RuntimeException {
    private static final long serialVersionUID = 1L;
	
    public CarrierAlreadyPresent(String message) {
        super(message);
    }
}