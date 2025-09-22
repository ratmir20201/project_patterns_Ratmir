import java.util.ArrayList;
import java.util.List;


public class practice {
    public static void main(String[] args) {
        Order order = new Order();
        order.addItem(new Item("Laptop", 1200.00, 1));
        order.addItem(new Item("Mouse", 25.00, 2));

        System.out.println(order + " " + order.getTotalSum());

        IPayment payment = new CreditCardPayment("4111-1111-1111-1111");
        IDelivery delivery = new CourierDelivery("ул Пушкина");

        DiscountCalculator discountCalculator = new DiscountCalculator();
        double total_sum = discountCalculator.calculateDiscount(order, new PercentageDiscountRule(0.1));

        System.out.println(order + " " + total_sum);

        INotification emailNotification = new EmailNotification();
        emailNotification.sendNotification("Оплата прошла успешно, заказ доставляется.");

    }
}


class Item {
    public final String name;
    public final double price;
    public int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double lineTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " " + quantity + " " + price;
    }
}

class Order {
    public final List<Item> items = new ArrayList<>();

    public Order() {
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotalSum() {
        double summ = 0;
        for (Item it : items) summ += it.lineTotal();
        return summ;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                '}';
    }
}


interface IPayment {
    public void processPayment(double amount);
}

class CreditCardPayment implements IPayment {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата суммы: " + amount + " картой " + cardNumber);
    }
}

class PayPalPayment implements IPayment {
    private final String account;

    public PayPalPayment(String account) {
        this.account = account;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата суммы: " + amount + " через аккаунт " + account);
    }
}

class BankTransferPayment implements IPayment {
    private final String bank;

    public BankTransferPayment(String bank) {
        this.bank = bank;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата суммы: " + amount + " через банк " + bank);
    }
}

interface IDelivery {
    void deliverOrder(Order order);
}

class CourierDelivery implements IDelivery {
    private final String address;

    public CourierDelivery(String address) {
        this.address = address;
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Курьер доставляет заказ: " + order + " на адрес: " + address);
    }
}

class PostDelivery implements IDelivery {
    private final String address;

    public PostDelivery(String address) {
        this.address = address;
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Доставка по почте заказа: " + order + " на адрес: " + address);
    }
}

class PickUpPointDelivery implements IDelivery {
    private final String address;

    public PickUpPointDelivery(String address) {
        this.address = address;
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Доставка заказа: " + order + " на пункт выдачи по адресу: " + address);
    }
}

interface INotification {
    void sendNotification(String message);
}

class EmailNotification implements INotification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsNotification implements INotification {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS: " + message);
    }
}

interface DiscountRule {
    double apply(Order order);
}


class PercentageDiscountRule implements DiscountRule {
    private final double percent;

    public PercentageDiscountRule(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(Order order) {
        return order.getTotalSum() * (1 - percent);
    }
}

class DiscountCalculator {
    private final List<DiscountRule> rules = new ArrayList<>();

    public void addRule(DiscountRule rule) {
        rules.add(rule);
    }

    public double calculateDiscount(Order order, DiscountRule discountRule) {
        return discountRule.apply(order);
    }
}


