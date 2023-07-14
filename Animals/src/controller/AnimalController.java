package controller;

import model.*;
import view.AnimalType;
import view.PackAnimalType;
import view.PetType;

import java.util.ArrayList;
import java.util.List;

public class AnimalController {
    private List<Animal> zoo;
    public AnimalController(){
        zoo = new ArrayList<>();
    }
    public void add(Animal animal){
        if(zoo==null){
            zoo = new ArrayList<>();
        }
        zoo.add(animal);
    }

    public List<Animal> list(PetType petType){
        List<Animal> petZoo = new ArrayList<>();
        for (Animal a:zoo) {
            switch (petType){
                case CAT:
                    if(a instanceof Cat)
                        petZoo.add(a);
                    break;
                case DOG:
                    if(a instanceof Dog)
                        petZoo.add(a);
                    break;
                case HAMSTER:
                    if(a instanceof Hamster)
                        petZoo.add(a);
                    break;
                case BACK: return null;
            }
        }
        return petZoo;
    }

    public List<Animal> list(PackAnimalType packAnimalType){
        List<Animal> packAnimalZoo = new ArrayList<>();
        for (Animal a:zoo) {
            switch (packAnimalType){
                case HORSE:
                    if(a instanceof Horse)
                        packAnimalZoo.add(a);
                    break;
                case CAMEL:
                    if(a instanceof Camel)
                        packAnimalZoo.add(a);
                    break;
                case DONKEY:
                    if(a instanceof Donkey)
                        packAnimalZoo.add(a);
                    break;
                case BACK: return null;
            }
        }
        return packAnimalZoo;
    }

    public List<String> listCommands(Animal animal){
        return animal.getCommands();
    }
    public Animal findAnimal(int number) throws ArrayIndexOutOfBoundsException{
        if(number>=zoo.size() || number<0)
            throw new ArrayIndexOutOfBoundsException("Несуществующий номер животного");
        return zoo.get(number);
    }

    public Animal addCommand(Animal animal, String command){
        for (Animal a:zoo) {
            if(a.equals(animal))
                a.studyCommand(command);
        }
        return animal;
    }
}
