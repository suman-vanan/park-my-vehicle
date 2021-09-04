package dev.sumanvanan.model;

import java.time.LocalDateTime;

public class ParkedMotorcycle extends Motorcycle {

    private final LocalDateTime startTime;

    public ParkedMotorcycle(String vehicleNumber, LocalDateTime startTime) {
        super(vehicleNumber);
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "ParkedMotorcycle{" +
                "startTime=" + startTime +
                "} " + super.toString();
    }
}
