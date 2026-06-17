package com.alpha.logisticsproject.exception;

public class DriverAlreadyPresent extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DriverAlreadyPresent(String message) {
        super(message);
    }
}