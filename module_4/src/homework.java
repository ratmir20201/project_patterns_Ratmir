import java.util.Scanner;

public class homework {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите тип транспорта: ");
        String user_input = scanner.nextLine();

        IVehicle transport = TransportService__homework.getTransport(user_input);
        System.out.println(transport);
    }
}

class TransportService__homework {
    public static IVehicle getTransport(String transportType) {
        VehicleFactory factory = null;

        if ("car".equalsIgnoreCase(transportType)) {
            factory = new CarFactory__homework();
        } else if ("motorcycle".equalsIgnoreCase(transportType)) {
            factory = new MotorcycleFactory__homework();
        } else if ("truck".equalsIgnoreCase(transportType)) {
            factory = new TruckFactory__homework();
        } else if ("bus".equalsIgnoreCase(transportType)) {
            factory = new BusFactory__homework();
        } else {
            throw new IllegalArgumentException("Unknown transport type: " + transportType);
        }

        return factory.createVehicle();
    }
}


interface IVehicle {
    void drive();

    void refuel();
}

class Car__homework implements IVehicle {
    String mark;
    String model;
    String fuelType;

    @Override
    public void drive() {
        System.out.println(model + " едет");
    }

    @Override
    public void refuel() {
        System.out.println("Машине необходимо топливо " + fuelType);
    }
}

class Bus__homework implements IVehicle {
    String mark;
    String model;
    String fuelType;

    @Override
    public void drive() {
        System.out.println(model + " едет");
    }

    @Override
    public void refuel() {
        System.out.println("Автобусу необходимо топливо " + fuelType);
    }
}


class Motorcycle__homework implements IVehicle {
    String motorcycleType;
    String engineSize;

    @Override
    public void drive() {
        System.out.println("Мотоцикл типа " + motorcycleType + " едет");
    }

    @Override
    public void refuel() {
        System.out.println("Мотоцикл заправляется");
    }
}

class Truck__homework implements IVehicle {
    String loadCapacity;
    String numberOfAxles;

    @Override
    public void drive() {
        System.out.println("Грузовик едет");
    }

    @Override
    public void refuel() {
        System.out.println("Грузовик заправляется");
    }
}

abstract class VehicleFactory {
    abstract IVehicle createVehicle();
}


class CarFactory__homework extends VehicleFactory {

    @Override
    IVehicle createVehicle() {
        return new Car__homework();
    }
}

class MotorcycleFactory__homework extends VehicleFactory {

    @Override
    IVehicle createVehicle() {
        return new Motorcycle__homework();
    }
}

class TruckFactory__homework extends VehicleFactory {

    @Override
    IVehicle createVehicle() {
        return new Truck__homework();
    }
}

class BusFactory__homework extends VehicleFactory {

    @Override
    IVehicle createVehicle() {
        return new Bus__homework();
    }
}