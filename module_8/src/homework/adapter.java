package homework;

public class adapter {
    public static void main(String[] args) {
        IPaymentProcessor paypal = new PayPalPaymentProcessor();
        paypal.processPayment(200.0);

        IPaymentProcessor stripe = new StripePaymentAdapter();
        stripe.processPayment(200.0);
    }
}

interface IPaymentProcessor {
    void processPayment(double amount);
}

class PayPalPaymentProcessor implements IPaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата через PayPal " + amount);
    }
}

class StripePaymentService {
    void makeTransaction(double totalAmount) {
        System.out.println("Оплата через Stripe " + totalAmount);
    }
}

class StripePaymentAdapter implements IPaymentProcessor {

    @Override
    public void processPayment(double amount) {
        StripePaymentService stripe = new StripePaymentService();
        stripe.makeTransaction(amount);
    }
}
