package practice;

import java.util.*;


public class observer {
    public static void main(String[] args) {
        StockExchange exchange = new StockExchange();

        Trader trader1 = new Trader("Ратмир");
        Trader trader2 = new Trader("Дмитрий");
        TradingBot bot = new TradingBot("KSPI", 120, 150);

        exchange.registerObserver(trader1);
        exchange.registerObserver(trader2);
        exchange.registerObserver(bot);

        exchange.setStockPrice("KSPI", 130);
        exchange.setStockPrice("KSPI", 115);
        exchange.setStockPrice("KSPI", 155);

        exchange.removeObserver(trader2);
        exchange.setStockPrice("KSPI", 140);
    }
}


interface IObserver {
    void update(String stockName, double price);
}

interface ISubject {
    void registerObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers(String stockName, double price);
}

class StockExchange implements ISubject {
    private List<IObserver> observers = new ArrayList<>();
    private Map<String, Double> stocks = new HashMap<>();

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String stockName, double price) {
        for (IObserver observer : observers) {
            observer.update(stockName, price);
        }
    }

    public void setStockPrice(String stockName, double price) {
        stocks.put(stockName, price);
        System.out.println("Биржа: новая цена " + stockName + " = " + price);
        notifyObservers(stockName, price);
    }
}

class Trader implements IObserver {
    private String name;

    public Trader(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("Трейдер " + name + ": новая цена " + stockName + " = " + price);
    }
}

class TradingBot implements IObserver {
    private String stockName;
    private double buyThreshold;
    private double sellThreshold;

    public TradingBot(String stockName, double buyThreshold, double sellThreshold) {
        this.stockName = stockName;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }

    @Override
    public void update(String stockName, double price) {
        if (!this.stockName.equals(stockName)) return;

        if (price <= buyThreshold) {
            System.out.println("Бот: покупаю " + stockName + " по цене " + price + "(ниже порога " + buyThreshold + ")");
        } else if (price >= sellThreshold) {
            System.out.println("Бот: продаю " + stockName + " по цене " + price + "(выше порога " + sellThreshold + ")");
        } else {
            System.out.println("Бот: наблюдаю за " + stockName + ", цена " + price);
        }
    }
}


