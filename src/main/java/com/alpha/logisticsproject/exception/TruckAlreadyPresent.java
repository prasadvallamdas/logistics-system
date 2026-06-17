package com.alpha.logisticsproject.exception;

public class TruckAlreadyPresent extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TruckAlreadyPresent(String message) {
        super(message);
    }
}