package dev.sumanvanan.model;

public class Vehicle {

    private final String vehicleNumber;
    private final Type type;

    public enum Type {
        CAR,
        MOTORCYCLE
    }

    public Vehicle(String vehicleNumber, Type type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
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
