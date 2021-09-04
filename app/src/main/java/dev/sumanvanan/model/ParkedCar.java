package dev.sumanvanan.model;

import java.time.LocalDateTime;

public class ParkedCar extends Car {

    private final LocalDateTime startTime;

    public ParkedCar(String vehicleNumber, LocalDateTime startTime) {
        super(vehicleNumber);
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "ParkedCar{" +
                "startTime=" + startTime +
                "} " + super.toString();
    }
}
