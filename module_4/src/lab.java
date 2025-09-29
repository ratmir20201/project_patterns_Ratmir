import java.util.Scanner;

public class lab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите тип транспорта: ");
        String user_input = scanner.nextLine();

        ITransport transport = TransportService.getTransport(user_input);
        System.out.println(transport);
    }
}

class TransportService {
    public static ITransport getTransport(String transportType) {
        TransportFactory factory = null;
        if ("car".equalsIgnoreCase(transportType)) {
            factory = new CarFactory();
        } else if ("motorcycle".equalsIgnoreCase(transportType)) {
            factory = new MotorcycleFactory();
        } else if ("plane".equalsIgnoreCase(transportType)) {
            factory = new PlaneFactory();
        } else {
            throw new IllegalArgumentException("Unknown transport type: " + transportType);
        }

        return factory.createTransport();
    }
}

interface ITransport {
    void move();

    void FuelUp();
}

class Car implements ITransport {
    @Override
    public void move() {
        System.out.println("Едет машина");
    }

    @Override
    public void FuelUp() {
        System.out.println("Машина заправляется");
    }
}

class Motorcycle implements ITransport {

    @Override
    public void move() {
        System.out.println("Едет мотоцикл");
    }

    @Override
    public void FuelUp() {
        System.out.println("Мотоцикл заправляется");
    }
}


class Plane implements ITransport {

    @Override
    public void move() {
        System.out.println("Самолет летит");
    }

    @Override
    public void FuelUp() {
        System.out.println("Самолет заправляется");
    }
}


abstract class TransportFactory {
    abstract ITransport createTransport();
}

class CarFactory extends TransportFactory {

    @Override
    ITransport createTransport() {
        return new Car();
    }
}

class MotorcycleFactory extends TransportFactory {

    @Override
    ITransport createTransport() {
        return new Motorcycle();
    }
}

class PlaneFactory extends TransportFactory {

    @Override
    ITransport createTransport() {
        return new Plane();
    }
}