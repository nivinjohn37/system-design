package impl;

import interfaces.ParkingObserver;
import interfaces.ParkingSpaceFactory;
import interfaces.ParkingSpot;
import interfaces.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    private List<ParkingSpot> parkingSpotList;
    private List<ParkingTicket> parkingTicketList;
    private List<ParkingObserver> observers;
    private ParkingSpaceFactory parkingSpaceFactory;

    public ParkingGarage(ParkingSpaceFactory parkingSpaceFactory){
        parkingSpotList = new ArrayList<>();
        parkingTicketList = new ArrayList<>();
        observers = new ArrayList<>();
        this.parkingSpaceFactory = parkingSpaceFactory;
    }

    public void addParkingSlot(){
        parkingSpotList.add(parkingSpaceFactory.createParkingSpot());
    }

    public ParkingTicket issueParkingTicket(Vehicle vehicle, long entryTime){
        for(ParkingSpot parkingSpot: parkingSpotList){
            if(parkingSpot.isAvailable()){
                parkingSpot.parkVehicle(vehicle);
                ParkingTicket ticket = new ParkingTicket(vehicle, entryTime);
                parkingTicketList.add(ticket);
                return ticket;
            }
        }
        return null;
    }

    public void removeVehicle(Vehicle vehicle){
        for(ParkingSpot spot: parkingSpotList){
            if(!spot.isAvailable() && spot instanceof CarParkingSpot carSpot){
                if(carSpot.getParkedVehicle().equals(vehicle)){
                    carSpot.removeVehicle(vehicle);
                    break;
                }
            }
        }
    }

    public double calculateParkingCost(ParkingTicket ticket){
        ticket.calculateCost();
        return ticket.getCost();
    }

    public void payParkingTicket(ParkingTicket ticket, double amount){
        System.out.println("Parking ticket paid : Rs." + amount);
    }

    public void addObserver(ParkingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ParkingObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ParkingTicket ticket) {
        for (ParkingObserver observer : observers) {
            observer.vehicleExited(ticket);
        }
    }
}
