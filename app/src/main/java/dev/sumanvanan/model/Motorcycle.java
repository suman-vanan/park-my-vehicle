package dev.sumanvanan.model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public String toString() {
        return "Motorcycle{} " + super.toString();
    }
}
