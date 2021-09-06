# Park My Vehicle

## Running the app

Pre-requisites:

- Java 11 (developed & tested on openjdk version "11.0.10")

To run the app with the default input file:

```bash
# Ensure you're in the project root directory
./gradlew run
```

To run the app with a specific input file as an argument:

```bash
# Ensure you're in the project root directory
./gradlew run --args="<absolute_path_of_file>"
```

To run tests:

```bash
./gradlew test
```

## Assumptions

- You can't admit a vehicle to a parking lot if there already is a vehicle with the same vehicle number within that
  parking lot
    - We're implicitly assuming that vehicle numbers are unique for each vehicle
    - Doing so will cause an exception to be thrown
- Input file follows the specified format

## Overview of Classes

- `App`: Entrypoint of app with a `main` method that parses an input file, processes it, and prints output to console
- `CarParkValet`: Key component that manages parking lots by admitting or exiting vehicles
- `InputParser`: Utility class that parses the input file string and returns a `ParsedInput` object
- `ParsedInput`: Domain object that represents parsed input
- `Vehicle`: Domain object representing a vehicle (either a car or a motorcycle). Both types of vehicles have the same
  functionality.
- `ParkedVehicle`: Domain object representing a vehicle that has been parked
- `VehicleParkTransaction`: Domain object representing a transaction (either entry or exit) for a particular `Vehicle`.
  Used by a `CarParkValet`
- `VehicleExitInfo`: Domain object returned by a `CarParkValet` when a `Vehicle` has attempted to exit from the car park

