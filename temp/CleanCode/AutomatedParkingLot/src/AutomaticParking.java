import impl.CarParkingSpaceFactory;
import impl.GenericVehicle;
import impl.ParkingGarage;
import impl.ParkingTicket;
import interfaces.ParkingObserver;
import interfaces.ParkingSpaceFactory;
import interfaces.Vehicle;

import java.time.Duration;
import java.time.Instant;

public class AutomaticParking {
    private ParkingGarage parkingGarage;

    public AutomaticParking(ParkingGarage parkingGarage) {
        this.parkingGarage = parkingGarage;
    }

    // Additional methods for automation logic
    public void automateParking() {
        System.out.println("Automated parking in progress...");
        // Implement automation logic (e.g., guiding vehicles to available spaces)
    }

    public void automateVehicleExit() {
        System.out.println("Automated vehicle exit in progress...");
        // Implement automation logic (e.g., opening gates)
    }
    public static void main(String[] args) {
        ParkingSpaceFactory carSpaceFactory = new CarParkingSpaceFactory();
        ParkingGarage garage  = new ParkingGarage(carSpaceFactory);
        AutomaticParking parkingGarage = new AutomaticParking(garage);


        garage.addParkingSlot();
        garage.addParkingSlot();

        garage.addObserver(new ParkingObserver() {
            @Override
            public void vehicleExited(ParkingTicket ticket) {
                double cost = ticket.getCost();
                System.out.println("Billing system: Vehicle exited. Cost: $" + cost);

            }
        });

        // Simulate vehicle entering and parking
        Vehicle car = new GenericVehicle("ABCD123", 2);
        long entryTimeStamp = Instant.now().getEpochSecond();
        ParkingTicket ticket = garage.issueParkingTicket(car, entryTimeStamp);
        parkingGarage.automateParking();

        // Simulate vehicle leaving
        long exitTimeStamp = Instant.now().plus(Duration.ofHours(1)).getEpochSecond();
        ticket.setExitTimeStamp(exitTimeStamp);
        double cost = garage.calculateParkingCost(ticket);
        garage.payParkingTicket(ticket,cost);
        garage.removeVehicle(car);
        garage.notifyObservers(ticket); // Notify observers
        parkingGarage.automateVehicleExit();
    }
}
