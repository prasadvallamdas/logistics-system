package com.alpha.logisticsproject.exception;

public class CargoAlreadyPresent extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CargoAlreadyPresent(String message) {
        super(message);
    }
}