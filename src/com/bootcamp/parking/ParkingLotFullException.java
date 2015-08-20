package com.bootcamp.parking;

public class ParkingLotFullException extends Exception {
	
	@Override
	public String getMessage() {
		return "Car cannot be parked because parking lot is full";
	}
}
