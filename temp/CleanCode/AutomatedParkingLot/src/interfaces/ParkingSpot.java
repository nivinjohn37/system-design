package interfaces;

public interface ParkingSpot {
    boolean isAvailable();
    void parkVehicle(Vehicle vehicle);
    void removeVehicle(Vehicle vehicle);
    Vehicle getParkedVehicle();
}
