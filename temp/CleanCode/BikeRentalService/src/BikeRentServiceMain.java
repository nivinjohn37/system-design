import impl.Bike;
import impl.BikeRentalCustomer;
import impl.BikeRentalService;
import interfaces.Customer;
import interfaces.RentalService;

public class BikeRentServiceMain {
    public static void main(String[] args) {
        // Create some bikes
        Bike bike1 = new Bike("001");
        Bike bike2 = new Bike("002");

        // Create a customer
        Customer customer1 = new BikeRentalCustomer("C001");

        // Create a rental service
        RentalService service = new BikeRentalService();

        // Customer rents a bike
        service.processRental(customer1, bike1);

        // Customer tries to rent the same bike again
        service.processRental(customer1, bike1);

        // Customer returns the bike
        customer1.returnBike(bike1);

        // Customer tries to return the same bike again
        customer1.returnBike(bike1);

        // Customer rents another bike
        service.processRental(customer1, bike2);
    }

}
