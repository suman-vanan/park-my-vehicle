package dev.sumanvanan;

import dev.sumanvanan.model.Vehicle;
import dev.sumanvanan.model.VehicleExitInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CarParkValetTest {

    @Test
    void givenEmptyLots_WhenAdmittingVehicles_ReceiveEmptyOptional() {
        CarParkValet testValet = new CarParkValet(0, 0);

        Vehicle testCar = new Vehicle("testVehicleNumber", Vehicle.Type.CAR);
        Vehicle testMotorcycle = new Vehicle("testVehicleNumber", Vehicle.Type.MOTORCYCLE);

        assertTrue(testValet.admit(testCar, LocalDateTime.now()).isEmpty());
        assertTrue(testValet.admit(testMotorcycle, LocalDateTime.now()).isEmpty());
    }

    @Test
    void givenNegativeNumberOfLots_WhenInstantiatingValet_ExceptionThrown() {
        Exception exceptionForBothNegative = assertThrows(IllegalArgumentException.class, () -> new CarParkValet(-1, -1));
        Exception exceptionForCarLotsNegative = assertThrows(IllegalArgumentException.class, () -> new CarParkValet(-1, 0));
        Exception exceptionForMotorcycleLotsNegative = assertThrows(IllegalArgumentException.class, () -> new CarParkValet(0, -1));

        String expectedMessage = "Number of parking lots cannot be a negative number";
        assertAll(
                () -> assertTrue(exceptionForBothNegative.getMessage().contains(expectedMessage)),
                () -> assertTrue(exceptionForCarLotsNegative.getMessage().contains(expectedMessage)),
                () -> assertTrue(exceptionForMotorcycleLotsNegative.getMessage().contains(expectedMessage))
        );
    }

    @Test
    void givenSingleLot_WhenAdmittingMultipleCars_ReceiveEmptyOptionalForSubsequentAdmissions() {
        CarParkValet testValet = new CarParkValet(1, 0);

        Vehicle testCar = new Vehicle("testVehicleNumber", Vehicle.Type.CAR);
        Vehicle anotherTestCar = new Vehicle("anotherTestVehicleNumber", Vehicle.Type.CAR);

        assertFalse(testValet.admit(testCar, LocalDateTime.now()).isEmpty());
        assertTrue(testValet.admit(anotherTestCar, LocalDateTime.now()).isEmpty());
    }

    // Should this test be combined with the one above, or kept separate?
    @Test
    void givenSingleLot_WhenAdmittingMultipleMotorcycles_ReceiveEmptyOptionalForSubsequentAdmissions() {
        CarParkValet testValet = new CarParkValet(0, 1);

        Vehicle testMotorcycle = new Vehicle("testVehicleNumber", Vehicle.Type.MOTORCYCLE);
        Vehicle anotherTestMotorcycle = new Vehicle("anotherTestVehicleNumber", Vehicle.Type.MOTORCYCLE);

        assertFalse(testValet.admit(testMotorcycle, LocalDateTime.now()).isEmpty());
        assertTrue(testValet.admit(anotherTestMotorcycle, LocalDateTime.now()).isEmpty());
    }


    @Test
    void givenEnoughLots_WhenAdmittingMultipleVehiclesConsecutively_AllottedLotNumberIncreases() {
        CarParkValet testValet = new CarParkValet(2, 0);

        Vehicle testCar = new Vehicle("testVehicleNumber", Vehicle.Type.CAR);
        Vehicle anotherTestCar = new Vehicle("anotherTestVehicleNumber", Vehicle.Type.CAR);

        Optional<Integer> firstLot = testValet.admit(testCar, LocalDateTime.now());
        assertTrue(firstLot.isPresent());
        assertEquals(1, firstLot.get());

        Optional<Integer> secondLot = testValet.admit(anotherTestCar, LocalDateTime.now());
        assertTrue(secondLot.isPresent());
        assertEquals(2, secondLot.get());
    }

    @Test
    void givenDisjointOccupiedLots_WhenAdmittingCar_AllottedToLotWithLowestNumber() {
        CarParkValet testValet = new CarParkValet(3, 0);

        Vehicle testCar1 = new Vehicle("testCar1", Vehicle.Type.CAR);
        Vehicle testCar2 = new Vehicle("testCar2", Vehicle.Type.CAR);
        Vehicle testCar3 = new Vehicle("testCar3", Vehicle.Type.CAR);

        testValet.admit(testCar1, LocalDateTime.now());
        testValet.admit(testCar2, LocalDateTime.now());
        testValet.exit(testCar1, LocalDateTime.now());

        Optional<Integer> occupiedCarLot = testValet.admit(testCar3, LocalDateTime.now());
        assertTrue(occupiedCarLot.isPresent());
        assertEquals(1, occupiedCarLot.get());
    }

    @Test
    void givenOccupiedLots_WhenAdmittingSameVehicleNumber_ExceptionThrown() {
        CarParkValet testValet = new CarParkValet(2, 0);

        Vehicle testVehicle = new Vehicle("duplicatedVehicleNumber", Vehicle.Type.CAR);
        Vehicle duplicatedTestVehicle = new Vehicle("duplicatedVehicleNumber", Vehicle.Type.CAR);

        testValet.admit(testVehicle, LocalDateTime.now());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> testValet.admit(duplicatedTestVehicle, LocalDateTime.now()));

        String expectedMessage = "Vehicle with same vehicleNumber already parked";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void givenParkedVehicles_WhenExit_CorrectParkingFeeReturned() {
        CarParkValet testValet = new CarParkValet(1, 0);

        Vehicle testVehicle = new Vehicle("testVehicle", Vehicle.Type.CAR);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAndOneMinuteLater = now.plusMinutes(61);

        testValet.admit(testVehicle, now);
        Optional<VehicleExitInfo> vehicleExitInfoOptional = testValet.exit(testVehicle, oneHourAndOneMinuteLater);

        assertTrue(vehicleExitInfoOptional.isPresent());
        assertEquals(4, vehicleExitInfoOptional.get().getParkingFee());
    }
}