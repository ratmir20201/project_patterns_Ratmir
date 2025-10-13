package lab;

import java.util.Scanner;

public class strategy {
    public static void main(String[] args) {
        DeliveryContext deliveryContext = new DeliveryContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип доставки: 1 - Стандартная, 2 - Экспресс, 3 - Международная");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                deliveryContext.setShippingStrategy(new StandardShippingStrategy());
                break;
            case "2":
                deliveryContext.setShippingStrategy(new ExpressShippingStrategy());
                break;
            case "3":
                deliveryContext.setShippingStrategy(new InternationalShippingStrategy());
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        System.out.print("Введите вес посылки (кг): ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Введите расстояние доставки (км): ");
        double distance = Double.parseDouble(scanner.nextLine());

        double cost = deliveryContext.calculateCost(weight, distance);
        System.out.print("Стоимость доставки: " + cost);
    }
}

interface IShippingStrategy {
    double calculateShippingCost(double weight, double distance);
}

class StandardShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 0.5 + distance * 0.1;
    }
}

class ExpressShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.75 + distance * 0.2) + 10;
    }
}

class InternationalShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 1.0 + distance * 0.5 + 15;
    }
}

class DeliveryContext {
    private IShippingStrategy _shippingStrategy;

    public void setShippingStrategy(IShippingStrategy strategy) {
        this._shippingStrategy = strategy;
    }

    public double calculateCost(double weight, double distance) {
        if (_shippingStrategy == null) {
            throw new IllegalStateException("Стратегия доставки не установлена.");
        }
        return _shippingStrategy.calculateShippingCost(weight, distance);
    }
}