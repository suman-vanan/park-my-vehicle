package dev.sumanvanan;

import dev.sumanvanan.model.ParkedVehicle;
import dev.sumanvanan.model.Vehicle;
import dev.sumanvanan.model.VehicleExitInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class CarParkValet {

    private static final int CAR_PARKING_FEE_PER_HOUR = 2;
    private static final int MOTORCYCLE_PARKING_FEE_PER_HOUR = 1;

    private final ParkedVehicle[] carLots;
    private final ParkedVehicle[] motorcycleLots;

    public CarParkValet(int numCarLots, int numMotorcycleLots) {
        carLots = new ParkedVehicle[numCarLots];
        motorcycleLots = new ParkedVehicle[numMotorcycleLots];
    }

    public Optional<Integer> admit(Vehicle vehicle, LocalDateTime startTime) {
        // only two types of vehicles in enum
        // if enum expands to more than two members, then perform additional checks
        if (vehicle.getType() == Vehicle.Type.CAR) {
            return admitVehicleToSpecificLotType(carLots, vehicle, startTime);
        } else {
            return admitVehicleToSpecificLotType(motorcycleLots, vehicle, startTime);
        }
    }

    private Optional<Integer> admitVehicleToSpecificLotType(ParkedVehicle[] parkingLots, Vehicle vehicle, LocalDateTime startTime) {
        for (int i = 0; i < parkingLots.length; i++) {
            ParkedVehicle parkingLot = parkingLots[i];
            // if lot is unoccupied, we can park in this lot
            // else, move on to the next lot
            if (parkingLot == null) {
                ParkedVehicle vehicleToBeParked = new ParkedVehicle(vehicle.getVehicleNumber(), vehicle.getType(), startTime);
                parkingLots[i] = vehicleToBeParked;
                return Optional.of(i + 1);
            }
        }
        // if execution reaches here, all parking lots are fully occupied
        return Optional.empty();
    }

    public Optional<VehicleExitInfo> exit(Vehicle vehicle, LocalDateTime endTime) {
        // only two types of vehicles in enum
        // if enum expands to more than two members, then perform additional checks
        if (vehicle.getType() == Vehicle.Type.CAR) {
            return exitVehicleToSpecificLotType(carLots, vehicle, endTime, CAR_PARKING_FEE_PER_HOUR);
        } else {
            return exitVehicleToSpecificLotType(motorcycleLots, vehicle, endTime, MOTORCYCLE_PARKING_FEE_PER_HOUR);
        }
    }

    private Optional<VehicleExitInfo> exitVehicleToSpecificLotType(ParkedVehicle[] parkingLots, Vehicle vehicle, LocalDateTime endTime, int parkingFeePerHour) {
        for (int i = 0; i < parkingLots.length; i++) {
            ParkedVehicle parkedVehicle = parkingLots[i];
            if (parkedVehicle != null && parkedVehicle.getVehicleNumber().equals(vehicle.getVehicleNumber())) {
                LocalDateTime startTime = parkedVehicle.getStartTime();
                Duration parkingDuration = Duration.between(startTime, endTime);
                long parkingMinutes = parkingDuration.toMinutes();
                if (parkingMinutes % 60 != 0) {
                    long excessMinutes = parkingMinutes - ((parkingMinutes / 60) * 60);
                    long minutesToAddForRoundingUpToNearestHour = 60 - excessMinutes;
                    parkingMinutes = parkingDuration.plusMinutes(minutesToAddForRoundingUpToNearestHour).toMinutes();
                }
                long parkingFee = (parkingMinutes / 60) * parkingFeePerHour;
                parkingLots[i] = null; // release parking lot
                return Optional.of(new VehicleExitInfo(i + 1, parkingFee));
            }
        }
        return Optional.empty();
    }
}
