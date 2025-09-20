public class lab {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.stop();
    }
}

//-----------------------------------------------------------------
class OrderService {
    public void createOrder(String productName, int quantity, double price) {
        processOrder("created", productName, quantity, price);
    }

    public void updateOrder(String productName, int quantity, double price) {
        processOrder("updated", productName, quantity, price);
    }

    private void processOrder(String action, String productName, int quantity, double price) {
        double totalPrice = quantity * price;
        System.out.println("Order for " + productName + " " + action + ". Total: " + totalPrice);
    }
}


//-----------------------------------------------------------------
abstract class Vehicle {
    public void start() {
        System.out.println(getClass().getSimpleName() + " is starting");
    }

    public void stop() {
        System.out.println(getClass().getSimpleName() + " is stopping");
    }
}

class Car extends Vehicle {
}

class Truck extends Vehicle {
}

//-----------------------------------------------------------------
class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }
}

//-----------------------------------------------------------------
class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void doSomething() {
        System.out.println("Doing something...");
    }
}

class Client {
    public void execute() {
        Singleton.getInstance().doSomething();
    }
}


//-----------------------------------------------------------------
class Circle {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Square {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double calculateArea() {
        return side * side;
    }
}


//-----------------------------------------------------------------
// Если надо можно логгировать снаружи
class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }
}
