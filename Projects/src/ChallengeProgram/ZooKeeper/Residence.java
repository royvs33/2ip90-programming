package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;

abstract class Residence {
    private ArrayList<Animal> animals;
    private int maxAnimals;

    public Residence(int maxAnimals) {
        this.maxAnimals = maxAnimals;
        animals = new ArrayList<>();
    }

    public int getMaxAnimals() {
        return maxAnimals;
    }

    public int getAnimalsAmount() {
        return animals.size();
    }

    public void printAnimals() {
        for (int i = 0; i < animals.size(); i ++) {
            Animal animal = animals.get(i);
            if (animal != null) {
                System.out.println(animal.getName() + " nickname " + animal.getNickName());
            } else {
                System.out.println("Unknown Animal Type");
            }
        }
    }

    abstract public String getName();

    public void addAnimal(Animal animal) throws Exception {
        if (animals.size() >= getMaxAnimals()) {
            throw new Exception(getName() + " is full, " + getMaxAnimals() + " animals already.");
        }
        animal.setResidence(this);
        animals.add(animal);
    }

    public void feedAnimals(String food) throws Exception {
        for (Animal animal : animals) {
            animal.checkAnimalFood(food);
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
