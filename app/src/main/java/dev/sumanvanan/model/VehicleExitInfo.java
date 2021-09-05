package dev.sumanvanan.model;

public class VehicleExitInfo {

    private final int releasedLotNumber;
    private final long parkingFee;

    public VehicleExitInfo(int releasedLotNumber, long parkingFee) {
        this.releasedLotNumber = releasedLotNumber;
        this.parkingFee = parkingFee;
    }

    public int getReleasedLotNumber() {
        return releasedLotNumber;
    }

    public long getParkingFee() {
        return parkingFee;
    }

    @Override
    public String toString() {
        return "VehicleExitInfo{" +
                "releasedLotNumber=" + releasedLotNumber +
                ", parkingFee=" + parkingFee +
                '}';
    }
}
