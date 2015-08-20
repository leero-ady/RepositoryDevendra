package com.bootcamp.parking;

public class CarExistInParkingLot extends Exception {
	@Override
	public String getMessage() {
		return "Car is already present in Parking lot";
	}
}
