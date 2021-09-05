package dev.sumanvanan.model;

import java.util.Objects;

public class Vehicle {

    private final String vehicleNumber;
    private final Type type;

    public enum Type {
        CAR,
        MOTORCYCLE
    }

    public Vehicle(String vehicleNumber, Type type) {
        this.vehicleNumber = Objects.requireNonNull(vehicleNumber, "vehicleNumber must not be null");
        this.type = Objects.requireNonNull(type, "type must not be null");
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", type=" + type +
                '}';
    }
}
