package practice;

import java.util.ArrayList;
import java.util.List;

public class command {
    public static void main(String[] args) {
        Light light = new Light();
        Television tv = new Television();
        AirConditioner ac = new AirConditioner();

        ICommand lightOn = new LightOnCommand(light);
        ICommand lightOff = new LightOffCommand(light);
        ICommand tvOn = new TVOnCommand(tv);
        ICommand tvOff = new TVOffCommand(tv);
        ICommand acOn = new ACOnCommand(ac);
        ICommand acOff = new ACOffCommand(ac);

        RemoteControl remote = new RemoteControl();

        System.out.println("=== Управление светом ===");
        remote.setCommand(lightOn);
        remote.pressExecute();
        remote.pressUndo();

        System.out.println("\n=== Макрокоманда: включить все устройства ===");
        List<ICommand> allOnCommands = new ArrayList<>();
        allOnCommands.add(lightOn);
        allOnCommands.add(tvOn);
        allOnCommands.add(acOn);

        MacroCommand macroOn = new MacroCommand(allOnCommands);
        remote.setCommand(macroOn);
        remote.pressExecute();

        System.out.println("\n=== Отмена макрокоманды ===");
        remote.pressUndo();
    }
}


interface ICommand {
    void execute();

    void undo();
}

class Light {
    public void on() {
        System.out.println("Свет включен");
    }

    public void off() {
        System.out.println("Свет выключен");
    }
}

class Television {
    public void on() {
        System.out.println("Телевизор включен");
    }

    public void off() {
        System.out.println("Телевизор выключен");
    }
}

class AirConditioner {
    public void on() {
        System.out.println("Кондиционер включен");
    }

    public void off() {
        System.out.println("Кондиционер выключен");
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

class TVOnCommand implements ICommand {
    private Television tv;

    public TVOnCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}

class TVOffCommand implements ICommand {
    private Television tv;

    public TVOffCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}

class ACOnCommand implements ICommand {
    private AirConditioner ac;

    public ACOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}

class ACOffCommand implements ICommand {
    private AirConditioner ac;

    public ACOffCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}

class MacroCommand implements ICommand {
    private List<ICommand> commands;

    public MacroCommand(List<ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (ICommand command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        // Отменяем команды в обратном порядке
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}

class RemoteControl {
    private ICommand slot;

    public void setCommand(ICommand command) {
        this.slot = command;
    }

    public void pressExecute() {
        if (slot != null) slot.execute();
    }

    public void pressUndo() {
        if (slot != null) slot.undo();
    }
}

