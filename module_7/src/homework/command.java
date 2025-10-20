package homework;

import java.util.Stack;

public class command {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();

        ICommand lightOn = new LightOnCommand(light);
        ICommand lightOff = new LightOffCommand(light);
        ICommand doorOpen = new DoorOpenCommand(door);
        ICommand doorClose = new DoorCloseCommand(door);
        ICommand tempUp = new IncreaseTemperatureCommand(thermostat);
        ICommand tempDown = new DecreaseTemperatureCommand(thermostat);

        RemoteControl remote = new RemoteControl();

        System.out.println("=== Управление светом ===");
        remote.pressButton(lightOn);
        remote.pressButton(lightOff);
        remote.pressUndo();

        System.out.println("\n=== Управление дверью ===");
        remote.pressButton(doorOpen);
        remote.pressButton(doorClose);
        remote.pressUndo();

        System.out.println("\n=== Управление температурой ===");
        remote.pressButton(tempUp);
        remote.pressButton(tempUp);
        remote.pressButton(tempDown);
        remote.pressUndo();
    }
}


interface ICommand {
    void execute();

    void undo();
}


class Light {
    public void on() {
        System.out.println("Свет включён.");
    }

    public void off() {
        System.out.println("Свет выключен.");
    }
}

class Door {
    public void open() {
        System.out.println("Дверь открыта.");
    }

    public void close() {
        System.out.println("Дверь закрыта.");
    }
}

class Thermostat {
    private int temperature = 22;

    public void increase() {
        temperature++;
        System.out.println("Температура увеличена до " + temperature + "°C");
    }

    public void decrease() {
        temperature--;
        System.out.println("Температура уменьшена до " + temperature + "°C");
    }
}


class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class DoorOpenCommand implements ICommand {
    private Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }
}

class DoorCloseCommand implements ICommand {
    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}

class IncreaseTemperatureCommand implements ICommand {
    private Thermostat thermostat;

    public IncreaseTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.increase();
    }

    @Override
    public void undo() {
        thermostat.decrease();
    }
}

class DecreaseTemperatureCommand implements ICommand {
    private Thermostat thermostat;

    public DecreaseTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.decrease();
    }

    @Override
    public void undo() {
        thermostat.increase();
    }
}


class RemoteControl {
    private Stack<ICommand> history = new Stack<>();

    public void pressButton(ICommand command) {
        command.execute();
        history.push(command);
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            ICommand lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Нет команд для отмены.");
        }
    }
}
