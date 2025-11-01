package interfaces;

import impl.ParkingTicket;

public interface ParkingObserver {
    void vehicleExited(ParkingTicket ticket);
}
