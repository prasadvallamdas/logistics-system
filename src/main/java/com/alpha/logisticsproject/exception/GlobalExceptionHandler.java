package com.alpha.logisticsproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.logisticsproject.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 1. Unified Handler for All Duplicate Data Conflicts (Status: 406 NOT_ACCEPTABLE)
	@ExceptionHandler({
		CarrierAlreadyPresent.class,
		UserAlreadyPresent.class,
		TruckAlreadyPresent.class,
		DriverAlreadyPresent.class,
		CargoAlreadyPresent.class,
		AddressAlreadyPresent.class,
		LoadingAlreadyPresent.class,
		UnloadingAlreadyPresent.class,
		LogisticsOrderAlreadyExist.class
	})
	public ResponseEntity<ResponseStructure<Object>> handleDuplicateResourceExceptions(RuntimeException ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		
		// Sets the internal status code to match your existing frontend requirements
		responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value()); 
		// Dynamically reads the precise custom message passed from your service layers
		responseStructure.setMessage(ex.getMessage() != null ? ex.getMessage() : "Resource entry already exists in database.");
		responseStructure.setData(null);
		
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	// 2. Unified Handler for All Missing Data Lookups (Status: 404 NOT_FOUND)
	@ExceptionHandler({
		OrderNotFoundException.class,
		AddressNotFoundException.class,
		CarrierNotFoundException.class
	})
	public ResponseEntity<ResponseStructure<Object>> handleResourceNotFoundExceptions(RuntimeException ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage() != null ? ex.getMessage() : "Requested logistics resource could not be found.");
		responseStructure.setData(null);
		
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	// 3. Fallback Global Handler for unexpected system exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<Object>> handleGeneralSystemFaults(Exception ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		responseStructure.setMessage("Internal System Runtime Error: " + ex.getMessage());
		responseStructure.setData(null);
		
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}