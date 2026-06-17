package com.alpha.logisticsproject.exception;

public class LoadingAlreadyPresent extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoadingAlreadyPresent(String message) {
        super(message);
    }
}