package homework;

public class template {
    public static void main(String[] args) {
        System.out.println("Приготовление чая:");
        Beverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println("\nПриготовление кофе:");
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

abstract class Beverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() {
        System.out.println("Кипячение воды...");
    }

    private void pourInCup() {
        System.out.println("Наливание в чашку...");
    }

    protected abstract void brew();

    protected abstract void addCondiments();
}

class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Заваривание чая...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавление лимона...");
    }
}

class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Заваривание кофе...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавление сахара и молока...");
    }
}


