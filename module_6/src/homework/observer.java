package homework;

import java.util.ArrayList;
import java.util.List;

public class observer {
    public static void main(String[] args) {
        CurrencyExchange exchange = new CurrencyExchange();

        IObserver bank = new BankObserver("Kaspi Банк");
        IObserver app = new MobileObserver("Freedom");

        exchange.registerObserver(bank);
        exchange.registerObserver(app);

        exchange.setRates(480.5f, 505.2f);
        exchange.setRates(482.0f, 507.0f);

        exchange.removeObserver(bank);
        exchange.setRates(485.3f, 510.1f);
    }
}

interface IObserver {
    void update(float usdRate, float eurRate);
}

interface ISubject {
    void registerObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers();
}

class CurrencyExchange implements ISubject {
    private List<IObserver> observers = new ArrayList<>();
    private float usdRate;
    private float eurRate;

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(usdRate, eurRate);
        }
    }

    public void setRates(float usd, float eur) {
        this.usdRate = usd;
        this.eurRate = eur;
        System.out.println("Курс обновлён: USD = " + usd + ", EUR = " + eur);
        notifyObservers();
    }
}

class BankObserver implements IObserver {
    private String name;

    public BankObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(float usdRate, float eurRate) {
        System.out.println(name + ": Новый курс: USD = " + usdRate + ", EUR = " + eurRate);
    }
}

class MobileObserver implements IObserver {
    private String name;

    public MobileObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(float usdRate, float eurRate) {
        System.out.println(name + ": Курсы обновлены: USD = " + usdRate + ", EUR = " + eurRate);
    }
}