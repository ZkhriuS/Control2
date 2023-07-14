package model;

import java.util.ArrayList;
import java.util.Objects;

public class Camel extends PackAnimal{
    public Camel(){

    }
    public Camel(Animal animal){
        this.name = animal.name;
        this.age = animal.age;
        validateCamel();
    }
    @Override
    public void eat() {
        System.out.println("Я ем верблюжью колючку");
    }
    @Override
    public void studyCommand(String command) {
        if(commands==null)
            commands = new ArrayList<>();
        commands.add(command);
    }

    public void validateCamel() throws NumberFormatException{
        if(age.getMonths()>12 ||age.getMonths()<1)
            throw new NumberFormatException("Неверное количество месяцев");
        if(age.getYears()>60 || age.getYears()<0)
            throw new NumberFormatException("Неверное количество лет");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Верблюд ");
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
        Camel animal = (Camel) o;
        return name.equals(animal.name) && Objects.equals(commands, animal.commands) && age.equals(animal.age);
    }
}
