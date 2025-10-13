package homework;

public class strategy {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();
        PaymentContext context_2 = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment());
        context_2.setPaymentStrategy(new PayPalPayment());

        double amount = 100500;

        context.executePayment(amount);
        context_2.executePayment(amount);
    }
}


interface IPaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " банковской картой.");
    }
}

class PayPalPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " через PayPal.");
    }
}

class CryptoPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата " + amount + "   с помощью криптовалюты.");
    }
}

class PaymentContext {
    private IPaymentStrategy paymentStrategy;

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Ошибка: стратегия оплаты не выбрана!");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}
