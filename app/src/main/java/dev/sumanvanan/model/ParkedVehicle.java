package dev.sumanvanan.model;

import java.time.LocalDateTime;

public class ParkedVehicle extends Vehicle {

    private final LocalDateTime startTime;

    public ParkedVehicle(String vehicleNumber, Type type, LocalDateTime startTime) {
        super(vehicleNumber, type);
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "ParkedVehicle{" +
                "startTime=" + startTime +
                "} " + super.toString();
    }
}
