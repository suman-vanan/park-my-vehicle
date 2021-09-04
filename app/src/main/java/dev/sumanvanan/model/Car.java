package dev.sumanvanan.model;

public class Car extends Vehicle {
    public Car(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }
}
