package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

abstract class Animal {
    private String nickName;
    private Residence residence;
    private ArrayList<String> allowedFood;

    public Animal(String nickName) {
        setNickName(nickName);
    }

    abstract public String getName();

    abstract public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals) throws Exception;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    /**
     * Checks if the animal wants to eat the food
     *
     * @param food type of food
     * @throws Exception exeption if not edible for the animal
     */
    public void checkAnimalFood(String food) throws Exception {
        if (!allowedFood.contains(food)) {
            throw new Exception(getName() + " " + getNickName() + " doesn't eat " + food);
        }
    }

    public void setAllowedFood(String... allowedFood) {
        this.allowedFood = new ArrayList<>(Arrays.asList(allowedFood));
    }

    /**
     * Get all Animals/Animal types (subclasses)
     *
     * @param animals an Array of all animals to check
     * @return an hashmap with unique Animal/Animal type classes
     */
    public HashMap<String, Integer> getAllClasses(ArrayList<Animal> animals) {
        HashMap<String, Integer> superClasses = new HashMap<>();
        for (int i = 0; i < animals.size(); i++) {
            Class c = animals.get(i).getClass();
            while (c != null) {
                //if not in HashMap already and not Object or Animal
                if (!c.getSimpleName().equals("Object") && !c.getSimpleName().equals("Animal") && superClasses.get(c.getSimpleName()) == null) {
                    superClasses.put(c.getSimpleName(), i);
                }
                c = c.getSuperclass();
            }
        }
        return superClasses;
    }
}
