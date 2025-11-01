package impl;

import interfaces.Customer;
import interfaces.Rentable;
import interfaces.RentalService;

// Class representing a Bike Rental Service
public class BikeRentalService implements RentalService {
    @Override
    public void processRental(Customer customer, Rentable item) {
        if (item.isRented()) {
            System.out.println("Cannot rent. Item is already rented.");
        } else {
            customer.rentBike((Bike) item);
        }
    }
}