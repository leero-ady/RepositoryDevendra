package com.bootcamp.parking;

public class CarDoesNotExistInParkingLot extends Exception{
	@Override
	public String getMessage() {
		return "Car Does not exist in Parking lot";
	}
}
