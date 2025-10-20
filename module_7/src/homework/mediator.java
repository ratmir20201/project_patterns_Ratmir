package homework;

import java.util.ArrayList;
import java.util.List;

public class mediator {
    public static void main(String[] args) {
        IMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Алиса");
        User user2 = new ChatUser(chatRoom, "Боб");
        User user3 = new ChatUser(chatRoom, "Чарли");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.sendMessage("Привет всем!");
        user2.sendMessage("Привет, Алиса!");
        user3.sendMessage("Всем привет!");
    }
}

interface IMediator {
    void sendMessage(String message, User sender);

    void addUser(User user);
}


class ChatRoom implements IMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }
}

abstract class User {
    protected IMediator mediator;
    protected String name;

    public User(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message);
}

class ChatUser extends User {
    public ChatUser(IMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}


