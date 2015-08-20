package com.bootcamp.parking.test;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import com.bootcamp.parking.AllParkingLotsFullException;
import com.bootcamp.parking.CarDoesNotExistInParkingLot;
import com.bootcamp.parking.CarExistInParkingLot;
import com.bootcamp.parking.ParkingLotAttendant;
import com.bootcamp.parking.ParkingLotForTraveller;
import com.bootcamp.parking.ParkingLotFullException;
import com.bootcamp.parking.ParkingLotOwner;
import com.bootcamp.parking.ParkingTicket;
import com.bootcamp.parking.Subscriber;
import com.bootcamp.parking.TravellerCar;

public class TestForParkingCar extends TestCase{
	
	ParkingLotOwner owner;
	
	@Override
	public void setUp() {
		owner = mock(ParkingLotOwner.class);
	}
	
	@Test
	public void testIfCarIsParkedSuccessfully() throws ParkingLotFullException, CarExistInParkingLot {
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(5,owner);
		TravellerCar car = new TravellerCar(1);
		assertNotNull(parkingLotForTraveller.parkCarInParkingLot(car));
		
	}
	
	@Test
	public void testIfCarParkingUnSuccessfullWhenParkingLotIsFull() {
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(0,owner);
		TravellerCar car = new TravellerCar(1);
		ParkingTicket ticket = new ParkingTicket();
		try {
			assertEquals(ticket,parkingLotForTraveller.parkCarInParkingLot(car));
		} catch (ParkingLotFullException e) {
			System.out.println(e.getMessage());
		} catch (CarExistInParkingLot e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testIfSameCarIsNotParkedAgain() throws ParkingLotFullException, CarExistInParkingLot {
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(5,owner);
		
		TravellerCar car = new TravellerCar(1);
		ParkingTicket ticket = parkingLotForTraveller.parkCarInParkingLot(car);
		try {
				
			assertEquals(ticket,parkingLotForTraveller.parkCarInParkingLot(car));
		} catch (ParkingLotFullException | CarExistInParkingLot e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testCarUnParkingSuccessfull() {
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(5,owner);
		TravellerCar car = new TravellerCar(1);
		ParkingTicket ticket;
		try {
			ticket = parkingLotForTraveller.parkCarInParkingLot(car);
			assertEquals(car,parkingLotForTraveller.unparkCarInParkingLot(ticket));
		} catch (ParkingLotFullException | CarExistInParkingLot e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CarDoesNotExistInParkingLot e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testCarUnParkingUnsuccessfull() {
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(5,owner);
		TravellerCar car = new TravellerCar(1);
		try {
			assertEquals(car,parkingLotForTraveller.unparkCarInParkingLot(new ParkingTicket()));
		} catch (CarDoesNotExistInParkingLot e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testParkingLotFullNotificationSent() {
		ParkingLotOwner lotOwner = mock(ParkingLotOwner.class);
		when(lotOwner.getCriteria(true)).thenReturn(new Integer(100));
		
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(1,lotOwner);
		
		TravellerCar car = new TravellerCar(1);
		
		try {
			ParkingTicket ticket = parkingLotForTraveller.parkCarInParkingLot(car);
		} catch (ParkingLotFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CarExistInParkingLot e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			verify(lotOwner, times(1)).notifySubscriber(true,parkingLotForTraveller);
		}
	}
	
	@Test
	public void testParkingLotFullNotificationSentOnlyOnce() {
		ParkingLotOwner lotOwner = mock(ParkingLotOwner.class);
		when(lotOwner.getCriteria(true)).thenReturn(new Integer(100));
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(1,lotOwner);
		TravellerCar car = new TravellerCar(1);
		TravellerCar car2 = new TravellerCar(2);
		try {
			ParkingTicket ticket = parkingLotForTraveller.parkCarInParkingLot(car);
			ParkingTicket ticket2 = parkingLotForTraveller.parkCarInParkingLot(car2);
		} catch (ParkingLotFullException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CarExistInParkingLot e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			verify(lotOwner, times(1)).notifySubscriber(true,parkingLotForTraveller);
		}
	}
	
	@Test
	public void testParkingLotHasSpaceAvailableNotificationSent() {
		ParkingLotOwner lotOwner = mock(ParkingLotOwner.class);
		when(lotOwner.getCriteria(true)).thenReturn(new Integer(100));
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(1,lotOwner);
		TravellerCar car = new TravellerCar(1);
		try {
			ParkingTicket ticket = parkingLotForTraveller.parkCarInParkingLot(car);
			parkingLotForTraveller.unparkCarInParkingLot(ticket);
		} catch (ParkingLotFullException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CarExistInParkingLot e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CarDoesNotExistInParkingLot e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			verify(lotOwner, times(1)).notifySubscriber(false,parkingLotForTraveller);
		}
	}
	
	@Test
	public void testParkingLotHasSpaceAvailableNotificationSentOnlyOnce() {
		ParkingLotOwner lotOwner = mock(ParkingLotOwner.class);
		when(lotOwner.getCriteria(true)).thenReturn(new Integer(100));
		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(2,lotOwner);
		TravellerCar car = new TravellerCar(1);
		TravellerCar carTwo = new TravellerCar(1);
		try {
			ParkingTicket ticket = parkingLotForTraveller.parkCarInParkingLot(car);
			ParkingTicket ticketTwo = parkingLotForTraveller.parkCarInParkingLot(carTwo);
			parkingLotForTraveller.unparkCarInParkingLot(ticket);
			parkingLotForTraveller.unparkCarInParkingLot(ticketTwo);
		} catch (ParkingLotFullException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CarExistInParkingLot e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CarDoesNotExistInParkingLot e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			verify(lotOwner, times(1)).notifySubscriber(false,parkingLotForTraveller);
		}
	}
	
	
	@Test
	public void shouldReturnParkingLotWithAvailableSpace() throws AllParkingLotsFullException {
		
		ParkingLotAttendant attendant = new ParkingLotAttendant();
		ParkingLotOwner lotOwner = mock(ParkingLotOwner.class);
		ParkingLotOwner lotOwnerTwo = mock(ParkingLotOwner.class);

		ParkingLotForTraveller parkingLotForTraveller = new ParkingLotForTraveller(1,lotOwner);
		ParkingLotForTraveller parkingLotForTravellerTwo = new ParkingLotForTraveller(2,lotOwnerTwo);
		TravellerCar car = new TravellerCar(1);
		TravellerCar carOne = new TravellerCar(1);
		parkingLotForTraveller.addSubscriber(attendant.getCriteria(true), attendant);
		attendant.addParkingLots(parkingLotForTraveller,parkingLotForTravellerTwo);
		assertEquals(true, attendant.getParkingLotWithParkingSpace() instanceof ParkingLotForTraveller );
	}
}
