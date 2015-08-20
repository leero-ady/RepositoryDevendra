package com.bootcamp.parking;

public class ParkingLotOwner implements SubcriptionWhenCarIsParked, SubscriptionWhenCarIsUnParked{
	
	public Integer criteriaWhileParking = new Integer(100);
	
	
	public ParkingLotOwner(Integer criteria) {
		this.criteriaWhileParking = criteria;
	}
	
	@Override
	public void notifyParkingCriteriaIsSatisfiedWhenCarIsParked() {
	
	}

	@Override
	public void notifySubscriber(boolean flag,ParkingLotForTraveller parkingLotForTraveller) {
		if(flag)
			notifyParkingCriteriaIsSatisfiedWhenCarIsParked();
		else
			notifyParkingCriteriaIsSatisfiedWhenCarIsUnParked();
	}

	@Override
	public Integer getCriteria(boolean flag) {
		if(flag) {
			return getCriteriaWhileParking();
		}else
			return getCriteriaWhileUnParking();
		
	}

	@Override
	public Integer getCriteriaWhileParking() {
		// TODO Auto-generated method stub
		return criteriaWhileParking;
	}

	@Override
	public void notifyParkingCriteriaIsSatisfiedWhenCarIsUnParked() {
		
	}

	@Override
	public Integer getCriteriaWhileUnParking() {
		
		return null;
	}

}
