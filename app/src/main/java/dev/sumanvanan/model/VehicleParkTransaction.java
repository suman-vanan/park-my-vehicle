package dev.sumanvanan.model;

import java.time.LocalDateTime;

public final class VehicleParkTransaction {

    private final boolean isEntry; // either entry or exit
    private final Vehicle vehicle;
    private final LocalDateTime time;

    public VehicleParkTransaction(boolean isEntry, Vehicle vehicle, LocalDateTime time) {
        this.isEntry = isEntry;
        this.vehicle = vehicle;
        this.time = time;
    }

    public boolean isEntry() {
        return isEntry;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "VehicleParkTransaction{" +
                "isEntry=" + isEntry +
                ", vehicle=" + vehicle +
                ", time=" + time +
                '}';
    }
}
