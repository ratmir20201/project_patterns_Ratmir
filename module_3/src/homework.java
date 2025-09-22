public class homework {
    public static void main(String[] args) {

    }
}

//-------------------------------------------------------------
class Order_1 {
    String productName;
    int quantity;
    double price;

    public Order_1(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}

class PriceCalculator {
    public double calculateTotalPrice(Order_1 order) {
        return order.quantity * order.price * 0.9;
    }
}

class PaymentProcessor {
    public void processPayment(String paymentDetails, double amount) {
        System.out.println("Оплата на сумму " + amount + " выполнена через: " + paymentDetails);
    }
}

class NotificationService {
    public void sendConfirmationEmail(String email) {
        System.out.println("Подтверждение заказа отправлено на: " + email);
    }
}


//-------------------------------------------------------------
class Employee {
    String name;
    double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String getName() {
        return name;
    }
}

interface SalaryCalculator {
    double calculateSalary(Employee employee);
}

class PermanentEmployeeSalaryCalculator implements SalaryCalculator {
    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() * 1.2;
    }
}

class ContractEmployeeSalaryCalculator implements SalaryCalculator {
    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() * 1.1;
    }
}

class InternEmployeeSalaryCalculator implements SalaryCalculator {
    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() * 0.8;
    }
}


//-------------------------------------------------------------
interface Printer {
    void print(String content);
}

interface Scanner {
    void scan(String content);
}

interface Fax {
    void fax(String content);
}


class AllInOnePrinter implements Printer, Scanner, Fax {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }

    @Override
    public void fax(String content) {
        System.out.println("Faxing: " + content);
    }
}

class BasicPrinter implements Printer {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }
}

class PrintScanPrinter implements Printer, Scanner {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }
}

//-------------------------------------------------------------
interface MessageSender {
    void sendMessage(String message);
}

class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class NotificationService_2 {
    private final MessageSender sender;

    public NotificationService_2(MessageSender sender) {
        this.sender = sender;
    }

    public void sendNotification(String message) {
        sender.sendMessage(message);
    }
}