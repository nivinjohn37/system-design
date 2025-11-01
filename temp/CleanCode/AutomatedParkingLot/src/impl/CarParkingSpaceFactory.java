package impl;

import interfaces.ParkingSpaceFactory;
import interfaces.ParkingSpot;

public class CarParkingSpaceFactory implements ParkingSpaceFactory {

    @Override
    public ParkingSpot createParkingSpot() {
        return new CarParkingSpot();
    }
}
