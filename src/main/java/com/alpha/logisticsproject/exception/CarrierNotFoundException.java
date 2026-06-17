package com.alpha.logisticsproject.exception;

public class CarrierNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CarrierNotFoundException(String message) {
        super(message);
    }
}