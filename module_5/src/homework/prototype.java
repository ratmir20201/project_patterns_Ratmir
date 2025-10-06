package homework;

import java.util.ArrayList;
import java.util.List;

public class prototype {
    public static void main(String[] args) {
        Order order1 = new Order(1500, "Карта");

        order1.addProduct(new Product("Ноутбук", 300000, 1));
        order1.addProduct(new Product("Мышка", 5000, 1));
        order1.addDiscount(new Discount("Скидка по купону", 10000));

        Order order2 = order1.clone();
        order2.products.get(0).name = "Монитор";

        System.out.println("Оригинал: " + order1);
        System.out.println("Клон: " + order2);
    }
}


interface IPrototype<T> {
    T clone();
}

class Product implements IPrototype<Product> {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public Product clone() {
        return new Product(name, price, quantity);
    }

    @Override
    public String toString() {
        return name + " (" + quantity + " шт, " + price + "₸)";
    }
}

class Discount implements IPrototype<Discount> {
    String description;
    double amount;

    public Discount(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public Discount clone() {
        return new Discount(description, amount);
    }

    @Override
    public String toString() {
        return description + ": -" + amount + "₸";
    }
}

class Order implements IPrototype<Order> {
    List<Product> products = new ArrayList<>();
    List<Discount> discounts = new ArrayList<>();
    double deliveryCost;
    String paymentMethod;

    public Order(double deliveryCost, String paymentMethod) {
        this.deliveryCost = deliveryCost;
        this.paymentMethod = paymentMethod;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    @Override
    public Order clone() {
        Order cloned = new Order(deliveryCost, paymentMethod);
        for (Product p : products)
            cloned.products.add(p.clone());
        for (Discount d : discounts)
            cloned.discounts.add(d.clone());
        return cloned;
    }

    @Override
    public String toString() {
        return "Заказ: " + products + ", доставка: " + deliveryCost + "₸, скидки: " + discounts +
                ", оплата: " + paymentMethod;
    }
}

