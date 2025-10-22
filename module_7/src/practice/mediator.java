package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mediator {
    public static void main(String[] args) {
        IMediator mediator = new ChatMediator();

        IUser alice = new User("Алиса", mediator);
        IUser bob = new User("Боб", mediator);
        IUser charlie = new User("Чарли", mediator);

        mediator.registerUser(alice, "Общий");
        mediator.registerUser(bob, "Общий");
        mediator.registerUser(charlie, "Общий");

        mediator.registerUser(alice, "Технобот");
        mediator.registerUser(bob, "Технобот");

        alice.send("Привет всем!", "Общий");
        bob.send("Привет, Алиса!", "Общий");
        charlie.send("Всем привет!", "Общий");

        System.out.println();

        alice.send("Как дела, бот?", "Технобот");
        bob.send("Все хорошо!", "Технобот");
    }
}


interface IMediator {
    void sendMessage(String message, IUser sender, String channel);

    void registerUser(IUser user, String channel);
}

interface IUser {
    void receive(String message, String channel);

    void send(String message, String channel);

    String getName();
}

class ChatMediator implements IMediator {
    private Map<String, List<IUser>> channels = new HashMap<>();

    @Override
    public void registerUser(IUser user, String channel) {
        channels.computeIfAbsent(channel, k -> new ArrayList<>()).add(user);
    }

    @Override
    public void sendMessage(String message, IUser sender, String channel) {
        List<IUser> users = channels.get(channel);
        if (users != null) {
            for (IUser user : users) {
                if (!user.equals(sender)) {
                    user.receive(message, channel);
                }
            }
        }
    }
}

class User implements IUser {
    private String name;
    private IMediator mediator;

    public User(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    @Override
    public void receive(String message, String channel) {
        System.out.println("[" + channel + "] " + name + " получил сообщение: " + message);
    }

    @Override
    public void send(String message, String channel) {
        System.out.println("[" + channel + "] " + name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this, channel);
    }

    @Override
    public String getName() {
        return name;
    }
}

