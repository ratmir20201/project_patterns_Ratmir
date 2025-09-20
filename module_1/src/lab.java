public class lab {
    public static void main(String[] args) {
        Worker worker1 = new Worker("Иван", 1, "Рабочий", 500, 40);
        Worker worker2 = new Worker("Анна", 2, "Рабочий", 450, 35);
        Manager manager1 = new Manager("Петр", 3, "Менеджер", 40000, 5000);

        Employee[] employees = {worker1, worker2, manager1};

        for (Employee emp : employees) {
            emp.showInfo();
            System.out.println("Зарплата: " + emp.calculateSalary());
            System.out.println();
        }
    }
}

abstract class Employee {
    String name;
    int id;
    String position;

    Employee(String name, int id, String position) {
        this.name = name;
        this.id = id;
        this.position = position;
    }

    abstract double calculateSalary();

    void showInfo() {
        System.out.println("Имя: " + name + ", ID: " + id + ", Должность: " + position);
    }
}

class Worker extends Employee {
    double hourlyRate;
    int hoursWorked;

    Worker(String name, int id, String position, double hourlyRate, int hoursWorked) {
        super(name, id, position);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class Manager extends Employee {
    double fixedSalary;
    double bonus;

    Manager(String name, int id, String position, double fixedSalary, double bonus) {
        super(name, id, position);
        this.fixedSalary = fixedSalary;
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return fixedSalary + bonus;
    }
}


