package model;

import java.util.ArrayList;
import java.util.Objects;

public class Dog extends Pet{
    public Dog(){

    }
    public Dog(Animal animal){
        this.name = animal.name;
        this.age = animal.age;
        validateDog();
    }
    @Override
    public void eat() {
        System.out.println("Я ем уток");
    }
    @Override
    public void studyCommand(String command) {
        if(commands==null)
            commands = new ArrayList<>();
        commands.add(command);
    }

    public void validateDog() throws NumberFormatException{
        if(age.getMonths()>12 ||age.getMonths()<1)
            throw new NumberFormatException("Неверное количество месяцев");
        if(age.getYears()>25 || age.getYears()<0)
            throw new NumberFormatException("Неверное количество лет");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Собака ");
        sb.append(name).append(" умеет ");
        if(commands!=null)
            for (String c:commands){
                sb.append(c).append("\n");
            }
        sb.append(age);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog animal = (Dog) o;
        return name.equals(animal.name) && Objects.equals(commands, animal.commands) && age.equals(animal.age);
    }
}
