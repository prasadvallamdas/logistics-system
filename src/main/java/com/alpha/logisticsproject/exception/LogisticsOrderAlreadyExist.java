package com.alpha.logisticsproject.exception;

public class LogisticsOrderAlreadyExist extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LogisticsOrderAlreadyExist(String message) {
        super(message);
    }
}