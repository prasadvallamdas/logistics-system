package com.alpha.logisticsproject.exception;

public class UnloadingAlreadyPresent extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnloadingAlreadyPresent(String message) {
        super(message);
    }
}