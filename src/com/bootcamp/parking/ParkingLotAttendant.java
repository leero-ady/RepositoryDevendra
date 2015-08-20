package com.bootcamp.parking;


import java.util.ArrayList;

import java.util.List;


public class ParkingLotAttendant implements Subscriber{

	public Integer criteriaWhileParking = new Integer(100);
	List<ParkingLotForTraveller> parkingLots = new ArrayList();
	
	public void addParkingLots(ParkingLotForTraveller ...parkingLotForTravellers){
		
		for(ParkingLotForTraveller lot : parkingLotForTravellers){
			
			parkingLots.add(lot);
			
		}
		
	}
	
	public ParkingLotForTraveller getParkingLotWithParkingSpace() throws AllParkingLotsFullException{
		
		if(parkingLots.isEmpty())
			throw new AllParkingLotsFullException();
		else
			return parkingLots.get(0);
	}


	@Override
	public void notifySubscriber(boolean flag,ParkingLotForTraveller parkingLotForTraveller) {
		if(flag)
			parkingLots.remove(parkingLotForTraveller);
		else
			addParkingLots(parkingLotForTraveller);
	}

	@Override
	public Integer getCriteria(boolean flag) {
		if(flag) {
			return criteriaWhileParking;
		}else
			return criteriaWhileParking;
		
	}

}
