import java.util.ArrayList;
import java.util.List;

public class lab {
    public static void main(String[] args) {

    }
}


//-------------------------------------------------------------
class Item_1 {
    public double price;
    // Какой-то класс с нужными свойствами
}

class Invoice {
    public int id;
    public List<Item_1> items;
    public double taxRate;

    public Invoice(int id, double taxRate) {
        this.id = id;
        this.taxRate = taxRate;
        this.items = new ArrayList<>();
    }
}

class InvoiceCalculator {
    public double calculateTotal(Invoice invoice) {
        double subTotal = 0;
        for (Item_1 item : invoice.items) {
            subTotal += item.price;
        }
        return subTotal + (subTotal * invoice.taxRate);
    }
}

class InvoiceRepository {
    public void saveToDatabase(Invoice invoice) {
        // Логика для сохранения счета-фактуры в базу данных
    }
}


//-------------------------------------------------------------
interface DiscountType {
    double getDiscount(double amount);
}

class RegularDiscount implements DiscountType {

    @Override
    public double getDiscount(double amount) {
        return amount;
    }
}

class SilverDiscount implements DiscountType {

    @Override
    public double getDiscount(double amount) {
        return amount * 0.9;
    }
}

class GoldDiscount implements DiscountType {

    @Override
    public double getDiscount(double amount) {
        return amount * 0.8;
    }
}


class DiscountCalculator_1 {
    public double CalculateDiscount(DiscountType discountType, double amount) {
        return discountType.getDiscount(amount);
    }
}


//-------------------------------------------------------------
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

class HumanWorker implements Workable, Eatable, Sleepable {
    @Override
    public void eat() {
        // .
    }

    @Override
    public void sleep() {
        // .
    }

    @Override
    public void work() {
        // .
    }
}

class RobotWorker implements Workable {

    @Override
    public void work() {
        // .
    }
}


//-------------------------------------------------------------
interface IEmailService {
    public void sendEmail(String message);
}

class EmailService implements IEmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Отправка Email:" + message);
    }
}

class Notification_1 {
    private IEmailService _emailService;

    public Notification_1(IEmailService service) {
        _emailService = service;
    }

    public void send(String message) {
        _emailService.sendEmail(message);
    }
}
