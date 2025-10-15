package practice;

import java.util.Scanner;

public class strategy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelBookingContext context = new TravelBookingContext();

        System.out.println("Выберите тип транспорта: 1 - Самолет, 2 - Поезд, 3 - Автобус");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                context.setStrategy(new PlaneCostStrategy());
                break;
            case "2":
                context.setStrategy(new TrainCostStrategy());
                break;
            case "3":
                context.setStrategy(new BusCostStrategy());
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        System.out.print("Введите расстояние (км): ");
        double distance = Double.parseDouble(scanner.nextLine());

        System.out.print("Введите класс обслуживания (эконом/бизнес): ");
        String serviceClass = scanner.nextLine();

        System.out.print("Есть ли скидка (да/нет): ");
        boolean hasDiscount = scanner.nextLine().equalsIgnoreCase("да");

        double cost = context.calculateTripCost(distance, serviceClass, hasDiscount);
        System.out.printf("Стоимость поездки: " + cost);
    }
}


interface ICostCalculationStrategy {
    double calculateCost(double distance, String serviceClass, boolean hasDiscount);
}

class PlaneCostStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String serviceClass, boolean hasDiscount) {
        double baseRate = 15.0;
        double cost = distance * baseRate;

        if (serviceClass.equalsIgnoreCase("бизнес")) cost *= 1.5;
        if (hasDiscount) cost *= 0.8;

        return cost;
    }
}

class TrainCostStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String serviceClass, boolean hasDiscount) {
        double baseRate = 5.0;
        double cost = distance * baseRate;

        if (serviceClass.equalsIgnoreCase("бизнес")) cost *= 1.3;
        if (hasDiscount) cost *= 0.9;

        return cost;
    }
}

class BusCostStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String serviceClass, boolean hasDiscount) {
        double baseRate = 2.5;
        double cost = distance * baseRate;

        if (serviceClass.equalsIgnoreCase("бизнес")) cost *= 1.2;
        if (hasDiscount) cost *= 0.85;

        return cost;
    }
}

class TravelBookingContext {
    private ICostCalculationStrategy strategy;

    public void setStrategy(ICostCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateTripCost(double distance, String serviceClass, boolean hasDiscount) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия не выбрана.");
        }
        return strategy.calculateCost(distance, serviceClass, hasDiscount);
    }
}

