import java.util.ArrayList;
import java.util.List;

public class practice {
    public static void main(String[] args) {
        UserManager manager = new UserManager();

        User u1 = new User("Alice", "alice@example.com", "Admin");
        User u2 = new User("Bob", "bob@example.com", "User");

        manager.addUser(u1);
        manager.addUser(u2);

        System.out.println("После добавления:");
        manager.printAllUsers();

        User updatedBob = new User("Bobby", "bob@example.com", "Admin");
        manager.updateUser(u2, updatedBob);

        System.out.println("\nПосле обновления:");
        manager.printAllUsers();

        manager.removeUser(u1);

        System.out.println("\nПосле удаления:");
        manager.printAllUsers();
    }
}

class User {
    public String name;
    public String email;
    public String role;

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return name + " (" + role + ") - " + email;
    }
}

class UserManager {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void updateUser(User oldUser, User newUser) {
        int index = users.indexOf(oldUser);
        if (index != -1) {
            users.set(index, newUser);
        }
    }

    public void printAllUsers() {
        for (User u : users) {
            System.out.println(u);
        }
    }
}

