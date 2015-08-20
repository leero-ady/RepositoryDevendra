package com.bootcamp.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ParkingLotForTraveller {
	
	private static int capacityOfParking;
	private ParkingLotOwner owner;
	private HashMap<ParkingTicket, TravellerCar> parkingLot = new HashMap<ParkingTicket, TravellerCar>();
	private HashMap<Integer, List<Subscriber>> subscriberList = new HashMap<Integer, List<Subscriber>>();
	
	public ParkingLotForTraveller(int capacity, ParkingLotOwner owner) {
		capacityOfParking = capacity;
		this.owner = owner;
		addSubscriber(owner.getCriteria(true),this.owner);
	}

	public void addSubscriber(Integer criteria, Subscriber subscriber) {
		List<Subscriber> temp = subscriberList.get(criteria);
		if(temp == null) {
			temp = new ArrayList<Subscriber>();
		}
		temp.add(subscriber);
		addSubscriberList(criteria,temp);
	}
	
	private void addSubscriberList(Integer criteria, List<Subscriber> subscriber) {
		subscriberList.put(criteria, subscriber);
	}
	
	private boolean isCarAlreadyParked(TravellerCar car) {
		if (parkingLot.containsValue(car))
			return true;
		return false;
	}

	public ParkingTicket parkCarInParkingLot(TravellerCar car) throws ParkingLotFullException, CarExistInParkingLot  {
		boolean flag = false;
		if (!isParkingLotFull()) {
			if (!isCarAlreadyParked(car)) {
				ParkingTicket ticket = new ParkingTicket();
				parkingLot.put(ticket, car);
				
				List<Subscriber> subList = subscriberList.get(getPercentageOfParkingLotFilled());
				
				if(subList != null) {
					for(Subscriber subscriber:subList){
							subscriber.notifySubscriber(true,this);
					}
				}
				return ticket;
			}else
				throw new CarExistInParkingLot();
		}else
			throw new ParkingLotFullException();
	}

	public TravellerCar unparkCarInParkingLot(ParkingTicket ticket) throws CarDoesNotExistInParkingLot{
		
		if (isCarParked(ticket)) {
			if(isParkingLotFull()) {	
				List<Subscriber> subList = subscriberList.get(getPercentageOfParkingLotFilled());
				if(subList != null) {
					for(Subscriber subscriber:subList){
							subscriber.notifySubscriber(false,this);
					}
				}
			}
			return parkingLot.remove(ticket);
		}
		throw new CarDoesNotExistInParkingLot();
	}

	private boolean isCarParked(ParkingTicket ticket) {
		if(parkingLot.containsKey(ticket))
			return true;
		return false;
	}

	private boolean isParkingLotFull() {
		if (parkingLot.size() >= capacityOfParking) {
			return true;
		}
		return false;
	}

	private boolean isParkingLotEmpty() {
		if (parkingLot.size() >= 0) {
			return true;
		}
		return false;
	}

	
	private void notifyOwnerParkingLotHasSpaceAvailable() {
		//owner.setParkingSpaceAvailable();
	}
	
	private Integer getPercentageOfParkingLotFilled() {
		return new Integer((parkingLot.size()/capacityOfParking)*100);
	}
	
	public boolean isNotFull() {
		return capacityOfParking-parkingLot.size() > 0;
	}
}
