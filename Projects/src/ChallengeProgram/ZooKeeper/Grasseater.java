package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

abstract class Grasseater extends Animal {
    public Grasseater(String nickName) {
        super(nickName);
        setAllowedFood("Hey", "Corn", "Grain", "Carrots");
    }

    public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals, String animalType) throws Exception {
        //Grasseater can only live in Enclosure
        if (!homeType.equals("Enclosure")) {
            throw new Exception(animalType + " doesn't live in " + homeType);
        }
        HashMap<String, Integer> allClasses = getAllClasses(residenceAnimals);
        //can't live with Carnivores
        Integer carnivoreIndex = allClasses.get("Carnivore");
        if (carnivoreIndex != null) {
            throw new Exception(animalType + " can't live with " + residenceAnimals.get(carnivoreIndex).getName());
        }
    }
}
