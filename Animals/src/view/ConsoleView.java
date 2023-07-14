package view;

import controller.AnimalController;
import model.*;

import java.io.*;
import java.util.List;

public class ConsoleView {
    private AnimalController controller;

    public ConsoleView(AnimalController controller){
        this.controller = controller;
    }
    public void menu(){
        try(Counter counter = new Counter();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Выберите действие:");
                System.out.println("1. Добавить животное\n" +
                        "2. Посмотреть животных\n" +
                        "3. Посмотреть команды животного\n" +
                        "4. Обучить животное новым командам\n" +
                        "5. Выйти");
                switch (inputAction(reader)) {
                    case ADD:
                        add(reader);
                        counter.add();
                        System.out.println("Counter: "+counter.getCounter());
                        break;
                    case LIST:
                        list(reader);
                        break;
                    case COMMAND_LIST:
                        listCommands(reader);
                        break;
                    case TRAIN:
                        train(reader);
                        break;
                    case EXIT:
                        return;
                    case NONE:
                        System.out.println("Повторите попытку");
                }
            }
        } catch (Exception e){
            System.out.println("Счетчик сломался");
            e.printStackTrace();
        }
    }
    private Actions inputAction(BufferedReader reader){
        try{
            switch (Integer.parseInt(reader.readLine())){
                case 1: return Actions.ADD;
                case 2: return Actions.LIST;
                case 3: return Actions.COMMAND_LIST;
                case 4: return Actions.TRAIN;
                case 5: return Actions.EXIT;
                default: throw new RuntimeException("Введен несуществующий пункт меню");
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка приведения типа");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Ошибка потока ввода");
            e.printStackTrace();
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return Actions.NONE;
    }

    private void add(BufferedReader reader){
            Animal animal = null;
            System.out.println("Выберите тип животного:");
            System.out.println("1. Домашнее животное\n" +
                    "2. Вьючное животное\n" +
                    "3. Назад");
            switch (inputAnimalType(reader)){
                case PET: animal = addPet(reader); break;
                case PACK_ANIMAL: animal = addPackAnimal(reader); break;
                case BACK: return;
                case NONE:
                    System.out.println("Повторите попытку");
            }
            controller.add(animal);
    }

    private AnimalType inputAnimalType(BufferedReader reader){
        try{
            switch (Integer.parseInt(reader.readLine())){
                case 1: return AnimalType.PET;
                case 2: return AnimalType.PACK_ANIMAL;
                case 3: return AnimalType.BACK;
                default: throw new RuntimeException("Введен несуществующий пункт меню");
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка приведения типа");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Ошибка потока ввода");
            e.printStackTrace();
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return AnimalType.NONE;
    }

    private Pet addPet(BufferedReader reader){
        while (true){
            System.out.println("Выберите тип домашнего животного:");
            System.out.println("1. Кот\n" +
                    "2. Собака\n" +
                    "3. Хомяк\n" +
                    "4. Назад");
            switch (inputPetType(reader)){
                case CAT: return new Cat(inputAnimal(new Cat(), reader));
                case DOG: return new Dog(inputAnimal(new Dog(), reader));
                case HAMSTER: return new Hamster(inputAnimal(new Hamster(), reader));
                case BACK: return null;
                case NONE:
                    System.out.println("Повторите попытку");
            }
        }
    }

    private Animal inputAnimal(Animal animal, BufferedReader reader){
        while (true) {
            try {
                System.out.println("Введите имя: ");
                animal.setName(reader.readLine());
                System.out.println("Введите количество лет: ");
                int years = Integer.parseInt(reader.readLine());
                System.out.println("Введите количество месяцев: ");
                int month = Integer.parseInt(reader.readLine());
                Age age = new Age(years, month);
                animal.setAge(age);
                return animal;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка приведения типа");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Ошибка потока ввода");
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private PetType inputPetType(BufferedReader reader){
        try{
            switch (Integer.parseInt(reader.readLine())){
                case 1: return PetType.CAT;
                case 2: return PetType.DOG;
                case 3: return PetType.HAMSTER;
                default: throw new RuntimeException("Введен несуществующий пункт меню");
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка приведения типа");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Ошибка потока ввода");
            e.printStackTrace();
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return PetType.NONE;
    }
    private PackAnimal addPackAnimal(BufferedReader reader){
        while (true){
            System.out.println("Выберите тип вьючного животного:");
            System.out.println("1. Лошадь\n" +
                    "2. Верблюд\n" +
                    "3. Осел\n" +
                    "4. Назад");
            switch (inputPackAnimalType(reader)){
                case HORSE: return new Horse(inputAnimal(new Horse(), reader));
                case CAMEL: return new Camel(inputAnimal(new Camel(), reader));
                case DONKEY: return new Donkey(inputAnimal(new Donkey(), reader));
                case BACK: return null;
                case NONE:
                    System.out.println("Повторите попытку");
            }
        }
    }

    private PackAnimalType inputPackAnimalType(BufferedReader reader){
        try{
            switch (Integer.parseInt(reader.readLine())){
                case 1: return PackAnimalType.HORSE;
                case 2: return PackAnimalType.CAMEL;
                case 3: return PackAnimalType.DONKEY;
                default: throw new RuntimeException("Введен несуществующий пункт меню");
            }
        } catch (NumberFormatException e){
            System.out.println("Ошибка приведения типа");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Ошибка потока ввода");
            e.printStackTrace();
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return PackAnimalType.NONE;
    }

    private boolean list(BufferedReader reader){
        List<Animal> list = null;
        boolean exit = false;
        while (!exit){
            System.out.println("Выберите тип животного:");
            System.out.println("1. Домашнее животное\n" +
                    "2. Вьючное животное\n" +
                    "3. Назад");
            AnimalType type = inputAnimalType(reader);
            switch (type){
                case PET:
                    System.out.println("Выберите тип домашнего животного:");
                    System.out.println("1. Кот\n" +
                            "2. Собака\n" +
                            "3. Хомяк\n" +
                            "4. Назад");
                    PetType petType = inputPetType(reader);
                    if(petType.equals(PetType.BACK))
                        return false;
                    list = controller.list(petType);
                    exit=true;
                    break;
                case PACK_ANIMAL:
                    System.out.println("Выберите тип вьючного животного:");
                    System.out.println("1. Лошадь\n" +
                            "2. Верблюд\n" +
                            "3. Осел\n" +
                            "4. Назад");
                    PackAnimalType packAnimalType = inputPackAnimalType(reader);
                    if(packAnimalType.equals(PackAnimalType.BACK))
                        return false;
                    list = controller.list(packAnimalType);
                    exit=true;
                    break;
                case BACK: return false;
            }
        }
        if(list==null){
            System.out.println("Питомник пуст");
            return false;
        }
        else{
            int i=1;
            for (Animal a: list) {
                System.out.println(i+". "+a);
                i++;
            }
            return true;
        }
    }

    private Animal listCommands(BufferedReader reader){
        Animal animal = chooseAnimal(reader);
        List<String> commands = controller.listCommands(animal);
        if(commands==null)
            System.out.println("Животное не обучено командам");
        else {
            for (String c : commands) {
                System.out.println(c);
            }
        }
        return animal;
    }

    private Animal chooseAnimal(BufferedReader reader){
        while (true){
            System.out.println("Выберите животное:");
            if(list(reader)) {
                try {
                    System.out.println("Введите номер животного: ");
                    int number = Integer.parseInt(reader.readLine())-1;
                    return controller.findAnimal(number);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Ошибка ввода номера");
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка приведения типа");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Ошибка потока ввода");
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void train(BufferedReader reader){
        Animal animal = listCommands(reader);
        while(true){
            System.out.println("Введите новую команду для животного:");
            try{
                String command = reader.readLine();
                animal = controller.addCommand(animal, command);
                return;
            } catch (NumberFormatException e){
                System.out.println("Ошибка приведения типа");
                e.printStackTrace();
            } catch (IOException e){
                System.out.println("Ошибка потока ввода");
                e.printStackTrace();
            } catch (RuntimeException e){
                e.printStackTrace();
            }
        }
    }
}
