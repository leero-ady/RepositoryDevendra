package com.bootcamp.parking;

public interface Subscriber {
	public void notifySubscriber(boolean flag,ParkingLotForTraveller parkingLotForTraveller);
	public Integer getCriteria(boolean flag);
}
