package impl;

import interfaces.Vehicle;

public class GenericVehicle implements Vehicle {
    private String licensePlate;
    private int size;

    public GenericVehicle(String licensePlate, int size){
        this.licensePlate = licensePlate;
        this.size = size;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public int getSize() {
        return size;
    }
}
