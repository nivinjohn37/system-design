package impl;

import interfaces.ParkingSpot;
import interfaces.Vehicle;

public class CarParkingSpot implements ParkingSpot {
    private boolean isAvailable = true;
    private Vehicle parkedVehicle;

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        this.parkedVehicle = null;
        this.isAvailable = false;
    }

    @Override
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
