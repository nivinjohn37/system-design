package impl;

import interfaces.Customer;

public class BikeRentalCustomer implements Customer {
    private String customerId;

    public BikeRentalCustomer(String customerId){
        this.customerId = customerId;
    }
    @Override
    public void rentBike(Bike b) {
        b.rent();
        System.out.println("Customer " + customerId + " rented bike: " + b.getBikeId());
    }

    @Override
    public void returnBike(Bike b) {
        b.returnBike();
        System.out.println("Customer " + customerId + " returned bike: " + b.getBikeId());

    }
}
