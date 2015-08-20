package com.bootcamp.parking;

import java.util.List;

public interface CriteriaForSelectingParkingLot {

	public List<ParkingLotForTraveller> sortParkingLots(List<ParkingLotForTraveller> parkingLots);
	
}
