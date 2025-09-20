import java.util.*;


public class practice {
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2020, 4, "Автомат");
        Car car2 = new Car("Lada", "Vesta", 2018, 4, "Механика");
        Motorcycle moto1 = new Motorcycle("Yamaha", "R1", 2021, "Спортбайк", false);

        Garage garage1 = new Garage();
        Garage garage2 = new Garage();

        garage1.addVehicle(car1);
        garage1.addVehicle(moto1);
        garage2.addVehicle(car2);

        Fleet fleet = new Fleet();
        fleet.addGarage(garage1);
        fleet.addGarage(garage2);

        garage1.showVehicles();
        garage2.showVehicles();

        System.out.println("\nПоиск по модели 'Camry':");
        fleet.findVehicle("Camry");

        System.out.println("\nУдаляем машину из гаража 1...");
        garage1.removeVehicle(moto1);
        garage1.showVehicles();
    }
}

abstract class Vehicle {
    String brand;
    String model;
    int year;

    Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    void startEngine() {
        System.out.println(brand + " " + model + " завелся");
    }

    void stopEngine() {
        System.out.println(brand + " " + model + " заглушен");
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }
}

class Car extends Vehicle {
    int doors;
    String transmission;

    Car(String brand, String model, int year, int doors, String transmission) {
        super(brand, model, year);
        this.doors = doors;
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return super.toString() + " | Дверей: " + doors + ", КПП: " + transmission;
    }
}

class Motorcycle extends Vehicle {
    String bodyType;
    boolean hasBox;

    Motorcycle(String brand, String model, int year, String bodyType, boolean hasBox) {
        super(brand, model, year);
        this.bodyType = bodyType;
        this.hasBox = hasBox;
    }

    @Override
    public String toString() {
        return super.toString() + " | Кузов: " + bodyType + ", Бокс: " + (hasBox ? "да" : "нет");
    }
}

class Garage {
    List<Vehicle> vehicles = new ArrayList<>();

    void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    void removeVehicle(Vehicle v) {
        vehicles.remove(v);
    }

    void showVehicles() {
        System.out.println("В гараже:");
        for (Vehicle v : vehicles) {
            System.out.println(" - " + v);
        }
    }
}

class Fleet {
    List<Garage> garages = new ArrayList<>();

    void addGarage(Garage g) {
        garages.add(g);
    }

    void removeGarage(Garage g) {
        garages.remove(g);
    }

    void findVehicle(String model) {
        for (Garage g : garages) {
            for (Vehicle v : g.vehicles) {
                if (v.model.equalsIgnoreCase(model)) {
                    System.out.println("Найдено: " + v);
                }
            }
        }
    }
}