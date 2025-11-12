package practice;

import java.util.ArrayList;
import java.util.List;

public class composite {
    public static void main(String[] args) {
        Employee e1 = new Employee("Работник1", "Работа1", 10000);
        Employee e2 = new Employee("Работник2", "Работа2", 100000);
        Employee e3 = new Employee("Работник3", "Работа3", 20000);

        Department devDept = new Department("Отдел1");
        devDept.add(e1);
        devDept.add(e2);

        Department mainDept = new Department("Главный отдел");
        mainDept.add(devDept);
        mainDept.add(e3);

        mainDept.showStructure("");
        System.out.println(mainDept.getBudget());
        System.out.println(mainDept.getEmployeeCount());
    }
}


abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public abstract void showStructure(String prefix);

    public abstract double getBudget();

    public abstract int getEmployeeCount();
}

class Employee extends OrganizationComponent {
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        super(name);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void showStructure(String prefix) {
        System.out.println(prefix + "- " + name + " (" + position + ")");
    }

    @Override
    public double getBudget() {
        return salary;
    }

    @Override
    public int getEmployeeCount() {
        return 1;
    }
}

class Department extends OrganizationComponent {
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    public void add(OrganizationComponent component) {
        components.add(component);
    }

    @Override
    public void showStructure(String prefix) {
        System.out.println(prefix + "+ " + name);
        for (OrganizationComponent c : components) {
            c.showStructure(prefix + "  ");
        }
    }

    @Override
    public double getBudget() {
        double total = 0;
        for (OrganizationComponent c : components) {
            total += c.getBudget();
        }
        return total;
    }

    @Override
    public int getEmployeeCount() {
        int count = 0;
        for (OrganizationComponent c : components) {
            count += c.getEmployeeCount();
        }
        return count;
    }
}

