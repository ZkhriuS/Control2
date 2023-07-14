package model;

import java.util.List;
import java.util.Objects;

public abstract class Animal {
    protected String name;
    protected List<String> commands;
    protected Age age;
    public abstract void eat();
    public abstract void studyCommand(String command);
    public List<String> getCommands(){
        return commands;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("name='").append(name).append('\'');
        sb.append(", commands=").append(commands);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return name.equals(animal.name) && Objects.equals(commands, animal.commands) && age.equals(animal.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, commands, age);
    }
}
