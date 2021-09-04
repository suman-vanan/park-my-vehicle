package dev.sumanvanan.model;

import java.util.Queue;

public final class ParsedInput {

    private final int numOfCarParkingLots;
    private final int numOfMotorcycleParkingLots;
    private final Queue<VehicleParkTransaction> vehicleParkTransactions;

    public ParsedInput(int numOfCarParkingLots, int numOfMotorcycleParkingLots, Queue<VehicleParkTransaction> vehicleParkTransactions) {
        this.numOfCarParkingLots = numOfCarParkingLots;
        this.numOfMotorcycleParkingLots = numOfMotorcycleParkingLots;
        this.vehicleParkTransactions = vehicleParkTransactions;
    }

    public int getNumOfCarParkingLots() {
        return numOfCarParkingLots;
    }

    public int getNumOfMotorcycleParkingLots() {
        return numOfMotorcycleParkingLots;
    }

    public Queue<VehicleParkTransaction> getVehicleParkTransactions() {
        return vehicleParkTransactions;
    }
}
