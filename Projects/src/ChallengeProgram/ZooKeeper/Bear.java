package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Bear extends Animal {
    private ArrayList<String> allowedFood;

    public Bear(String nickName) {
        super(nickName);
        setAllowedFood("Meat", "Chicken", "Carrots");
    }

    @Override
    public String getName() {
        return "Bear";
    }

    @Override
    public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals) throws Exception {
        HashMap<String, Integer> allClasses = getAllClasses(residenceAnimals);
        //Bears can't live with Carnivores
        Integer carnivoreIndex = allClasses.get("Carnivore");
        if (carnivoreIndex != null) {
            throw new Exception("Bear can't live with " + residenceAnimals.get(carnivoreIndex).getName());
        }
    }
}
