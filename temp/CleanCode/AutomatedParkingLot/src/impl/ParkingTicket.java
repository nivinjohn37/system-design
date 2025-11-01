package impl;

import interfaces.Vehicle;

public class ParkingTicket {
    private static int ticketCounter = 0;
    private int ticketId;
    private long entryTimeStamp;
    private long exitTimeStamp;
    private Vehicle vehicle;
    private double cost;

    public ParkingTicket(Vehicle vehicle, long entryTimeStamp){
        this.ticketId = ++ticketCounter;
        this.vehicle = vehicle;
        this.entryTimeStamp = entryTimeStamp;
    }

    public void setExitTimeStamp(long exitTimeStamp){
        this.exitTimeStamp = exitTimeStamp;
    }

    public void calculateCost(){
        long duration = exitTimeStamp - entryTimeStamp;
        cost = duration * 0.01;
    }

    public double getCost(){
        return cost;
    }
}
