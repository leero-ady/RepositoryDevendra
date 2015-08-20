package com.bootcamp.parking;

public interface SubcriptionWhenCarIsParked extends Subscriber {
	
	public void notifyParkingCriteriaIsSatisfiedWhenCarIsParked();
	public Integer getCriteriaWhileParking();
}
