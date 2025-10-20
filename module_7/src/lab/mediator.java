package lab;

import java.util.ArrayList;
import java.util.List;

public class mediator {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediator();

        User user1 = new User(chatMediator, "Алиса");
        User user2 = new User(chatMediator, "Боб");
        User user3 = new User(chatMediator, "Чарли");

        chatMediator.registerColleague(user1);
        chatMediator.registerColleague(user2);
        chatMediator.registerColleague(user3);

        user1.send("Привет всем!");
        user2.send("Привет, Алиса!");
        user3.send("Всем привет!");
    }
}


interface IMediator {
    void sendMessage(String message, Colleague sender);
}

abstract class Colleague {
    protected IMediator mediator;

    public Colleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receiveMessage(String message);
}

class ChatMediator implements IMediator {
    private List<Colleague> colleagues;

    public ChatMediator() {
        this.colleagues = new ArrayList<>();
    }

    public void registerColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void sendMessage(String message, Colleague sender) {
        for (Colleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receiveMessage(message);
            }
        }
    }
}

class User extends Colleague {
    private String name;

    public User(IMediator mediator, String name) {
        super(mediator);
        this.name = name;
    }

    public void send(String message) {
        System.out.println(name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}

