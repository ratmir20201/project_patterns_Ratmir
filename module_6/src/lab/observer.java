package lab;

import java.util.ArrayList;
import java.util.List;

public class observer {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        WeatherDisplay mobileApp = new WeatherDisplay("Мобильное приложение");
        WeatherDisplay digitalBillboard = new WeatherDisplay("Электронное табло");

        weatherStation.registerObserver(mobileApp);
        weatherStation.registerObserver(digitalBillboard);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);

        weatherStation.removeObserver(digitalBillboard);
        weatherStation.setTemperature(28.0f);
    }
}

interface IObserver {
    void update(float temperature);
}

interface ISubject {
    void registerObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers();
}

class WeatherStation implements ISubject {
    private final List<IObserver> observers = new ArrayList<>();
    private float temperature;

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
            observer.update(temperature);
        }
    }

    public void setTemperature(float newTemperature) {
        System.out.println("Изменение температуры: " + newTemperature);
        this.temperature = newTemperature;
        notifyObservers();
    }
}

class WeatherDisplay implements IObserver {
    private final String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " показывает новую температуру: " + temperature);
    }
}
