package lab;

public class command {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Television tv = new Television();

        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);

        ICommand tvOn = new TelevisionOnCommand(tv);
        ICommand tvOff = new TelevisionOffCommand(tv);

        RemoteControl remote = new RemoteControl();

        System.out.println("=== Управление светом ===");
        remote.setCommands(lightOn, lightOff);
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();

        System.out.println("\n=== Управление телевизором ===");
        remote.setCommands(tvOn, tvOff);
        remote.pressOnButton();
        remote.pressOffButton();
    }
}

interface ICommand {
    void execute();

    void undo();
}

class LightOnCommand implements ICommand {
    private final Light light;

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
    private final Light light;

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

class TelevisionOnCommand implements ICommand {
    private final Television tv;

    public TelevisionOnCommand(Television tv) {
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

class TelevisionOffCommand implements ICommand {
    private final Television tv;

    public TelevisionOffCommand(Television tv) {
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

class Light {
    public void on() {
        System.out.println("Свет включен.");
    }

    public void off() {
        System.out.println("Свет выключен.");
    }
}

class Television {
    public void on() {
        System.out.println("Телевизор включен.");
    }

    public void off() {
        System.out.println("Телевизор выключен.");
    }
}

class RemoteControl {
    private ICommand onCommand;
    private ICommand offCommand;

    public void setCommands(ICommand onCommand, ICommand offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void pressOnButton() {
        if (onCommand != null)
            onCommand.execute();
    }

    public void pressOffButton() {
        if (offCommand != null)
            offCommand.execute();
    }

    public void pressUndoButton() {
        if (onCommand != null)
            onCommand.undo();
    }
}

