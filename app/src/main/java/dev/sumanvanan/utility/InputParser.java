package dev.sumanvanan.utility;

import dev.sumanvanan.model.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public final class InputParser {

    private static final String ENTER = "Enter";
    private static final String EXIT = "Exit";
    private static final String CAR = "car";
    private static final String MOTORCYCLE = "motorcycle";

    private InputParser() {
    }

    public static ParsedInput parse(String input) {
        String[] lines = input.split("\\R");
        // Java 8 provides an “\R” pattern that matches any Unicode line-break sequence and covers all the newline characters for different operating systems

        int[] firstLineNums = parseFirstLine(lines[0]);
        Queue<VehicleParkTransaction> vehicleParkTransactions = parseSubsequentLines(Arrays.copyOfRange(lines, 1, lines.length));

        return new ParsedInput(firstLineNums[0], firstLineNums[1], vehicleParkTransactions);
    }

    private static int[] parseFirstLine(String firstLine) {
        Scanner scanner = new Scanner(firstLine);
        int numOfCarParkingLots = scanner.nextInt();
        int numOfMotorcycleParkingLots = scanner.nextInt();
        // fixme: apparently there's no need to close Scanner when instantiated with a parameter of type String
        // source: https://stackoverflow.com/a/17945827
        // is this true?
        return new int[]{numOfCarParkingLots, numOfMotorcycleParkingLots};
    }

    private static Queue<VehicleParkTransaction> parseSubsequentLines(String[] lines) {
        Queue<VehicleParkTransaction> result = new LinkedList<>();
        Map<String, Vehicle> vehicleNumberToVehicle = new HashMap<>();
        for (String line : lines) {
            Scanner scanner = new Scanner(line);
            String transactionType = scanner.next();
            if (ENTER.equals(transactionType)) {
                String vehicleType = scanner.next();
                String vehicleNumber = scanner.next();
                String timestampString = scanner.next();

                generateVehicle(vehicleType, vehicleNumber).ifPresent(vehicle -> {
                    vehicleNumberToVehicle.put(vehicle.getVehicleNumber(), vehicle);
                    result.add(generateVehicleParkTransaction(true, vehicle, timestampString));
                });
            } else if (EXIT.equals(transactionType)) {
                String vehicleNumber = scanner.next();
                Vehicle vehicle = vehicleNumberToVehicle.get(vehicleNumber);
                String timestampString = scanner.next();

                result.add(generateVehicleParkTransaction(false, vehicle, timestampString));
            } else {
                throw new RuntimeException("Error parsing file");
            }
        }
        return result;
    }

    private static VehicleParkTransaction generateVehicleParkTransaction(boolean isEntry, Vehicle vehicle, String timestampString) {
        long timestamp = Long.parseLong(timestampString);
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                TimeZone.getDefault().toZoneId());
        return new VehicleParkTransaction(isEntry, vehicle, time);
    }

    private static Optional<Vehicle> generateVehicle(String vehicleType, String vehicleNumber) {
        if (CAR.equals(vehicleType)) {
            return Optional.of(new Vehicle(vehicleNumber, Vehicle.Type.CAR));
        } else if (MOTORCYCLE.equals(vehicleType)) {
            return Optional.of(new Vehicle(vehicleNumber, Vehicle.Type.MOTORCYCLE));
        } else {
            return Optional.empty();
        }
    }
}
