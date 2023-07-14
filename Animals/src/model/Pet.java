package model;

public abstract class Pet extends Animal{
    public void run(){
        System.out.println("Я умею бегать");
    }

    public void jump(){
        System.out.println("Я умею прыгать");
    }

    public abstract void eat();
}
