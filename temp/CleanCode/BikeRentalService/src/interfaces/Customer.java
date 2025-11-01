package interfaces;

import impl.Bike;

public interface Customer {
    void rentBike(Bike b);
    void returnBike(Bike b);
}
