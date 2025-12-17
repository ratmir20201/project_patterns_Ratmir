import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        Image image = new Image();
        CommandManager manager = new CommandManager();

        Command brighten = new BrightnessCommand(image, 10);
        Command contrast = new ContrastCommand(image, -15);

        manager.executeCommand(brighten);
        manager.undo();
        manager.redo();
        manager.executeCommand(contrast);
    }
}

interface Command {
    void execute();

    void undo();
}

class Image {
    private int brightness = 0;
    private int contrast = 0;

    public void increaseBrightness(int value) {
        brightness += value;
        System.out.println("Яркость равна: " + brightness);
    }

    public void decreaseBrightness(int value) {
        brightness -= value;
        System.out.println("Яркость равна: " + brightness);
    }

    public void increaseContrast(int value) {
        contrast += value;
        System.out.println("Контраст равен: " + contrast);
    }

    public void decreaseContrast(int value) {
        contrast -= value;
        System.out.println("Контраст равен: " + contrast);
    }
}

class BrightnessCommand implements Command {
    private Image image;
    private int value;

    public BrightnessCommand(Image image, int value) {
        this.image = image;
        this.value = value;
    }

    @Override
    public void execute() {
        image.increaseBrightness(value);
    }

    @Override
    public void undo() {
        image.decreaseBrightness(value);
    }
}

class ContrastCommand implements Command {
    private Image image;
    private int value;

    public ContrastCommand(Image image, int value) {
        this.image = image;
        this.value = value;
    }

    @Override
    public void execute() {
        image.increaseContrast(value);
    }

    @Override
    public void undo() {
        image.decreaseContrast(value);
    }
}


class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}

