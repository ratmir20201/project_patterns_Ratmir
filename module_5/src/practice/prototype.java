package practice;

import java.util.ArrayList;
import java.util.List;

public class prototype {
    public static void main(String[] args) {
        Character mage = new Character("Gandalf", 100, 20, 15, 90);
        mage.equipWeapon(new Weapon("Magic Staff", 40));
        mage.equipArmor(new Armor("Wizard Robe", 15));
        mage.addSkill(new Skill("Fireball", "Magic"));
        mage.addSkill(new Skill("Teleport", "Magic"));

        System.out.println("Оригинал персонажа:");
        System.out.println(mage);

        Character cloneMage = mage.clone();
        cloneMage.name = "Gandalf Clone";
        cloneMage.weapon.name = "Dark Staff";
        cloneMage.skills.get(0).name = "Shadow Ball";

        System.out.println("\nКлон персонажа:");
        System.out.println(cloneMage);

        System.out.println("\nОригинал после клонирования (не изменился):");
        System.out.println(mage);
    }
}

interface ICloneable<T> {
    T clone();
}


class Character implements ICloneable<Character> {
    public String name;
    public int health;
    public int strength;
    public int agility;
    public int intelligence;

    public Weapon weapon;
    public Armor armor;
    public List<Skill> skills = new ArrayList<>();

    public Character(String name, int health, int strength, int agility, int intelligence) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
    }

    @Override
    public Character clone() {
        Character copy = new Character(this.name, this.health, this.strength, this.agility, this.intelligence);

        if (this.weapon != null)
            copy.weapon = this.weapon.clone();

        if (this.armor != null)
            copy.armor = this.armor.clone();

        for (Skill s : this.skills)
            copy.skills.add(s.clone());

        return copy;
    }

}


class Weapon implements ICloneable<Weapon> {
    public String name;
    public int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public Weapon clone() {
        return new Weapon(this.name, this.damage);
    }

}

class Armor implements ICloneable<Armor> {
    public String name;
    public int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    @Override
    public Armor clone() {
        return new Armor(this.name, this.defense);
    }

}

class Skill implements ICloneable<Skill> {
    public String name;
    public String type; // Например: "Магическая", "Физическая"

    public Skill(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public Skill clone() {
        return new Skill(this.name, this.type);
    }

}


