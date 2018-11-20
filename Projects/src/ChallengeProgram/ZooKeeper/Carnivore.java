package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

abstract class Carnivore extends Animal {

    public Carnivore(String nickName) {
        super(nickName);
        setAllowedFood("Meat", "Chicken");
    }

    public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals, String animalType) throws Exception {
        HashMap<String, Integer> allClasses = getAllClasses(residenceAnimals);
        //animalType is only able to live with other Animals, so ArrayList should only contain animalType and this class

        if (allClasses.get(animalType) != null) {
            allClasses.remove(animalType);
        }
        if (allClasses.get("Carnivore") != null) {
            allClasses.remove("Carnivore");
        }
        //if it contains any elements, error
        if (allClasses.size() > 0) {
            int animalIndex = (int) allClasses.values().toArray()[0];
            throw new Exception(getName() + " can't live with " + residenceAnimals.get(animalIndex).getName());
        }
    }
}
