package ChallengeProgram.ZooKeeper;

import java.util.Arrays;
import java.util.HashMap;

public class MyZoo implements Zoo {
    /**
     * Arrays that holds all enclosures
     * Beware: the arguments are given using indexes starting at 1
     */
    private Enclosure[] enclosures;
    private Cage[] cages;
    private HashMap<String, Animal> nicknameMap;
    private HashMap<String, Integer> foodAmounts;


    public MyZoo() {
        //instantiate the cages array
        final int CAGE_AMOUNT = 20;
        cages = new Cage[CAGE_AMOUNT];
        //instantiate the enclosures
        final int ENCLOSURES_AMOUNT = 5;
        enclosures = new Enclosure[ENCLOSURES_AMOUNT];
        /*
        instantiate the nicknames HashMap to make sure nicknames are unique,
        key is the nickName and value is animal type
         */
        nicknameMap = new HashMap<>();
        foodAmounts = new HashMap<>();
        foodAmounts.put("Carrot", 50);
        foodAmounts.put("Meat", 500);
    }

    @Override
    public boolean accomodateAnimal(String animalSpecies, String animalNickname, String homeType, int homeNumber) {
        try {

            //check if nickname exists
            if (nicknameMap.get(animalNickname) != null) {
                throw new Exception(nicknameMap.get(animalNickname).getName() + " with nickname " + animalNickname + " already lives in our Zoo!");
            }

            //create Animal object
            Animal animal = createAnimal(animalSpecies, animalNickname);

            switch (homeType) {
                case "Cage": {
                    if (homeNumber < 0 || homeNumber >= cages.length) {
                        throw new Exception("Our Zoo doesn't have " + homeType + " number " + homeNumber);
                    }
                    if (cages[homeNumber] == null) {
                        cages[homeNumber] = new Cage();
                    }
                    //check if Animal is allowed in Residence
                    animal.checkAnimalInResidence(homeType, cages[homeNumber].getAnimals());
                    cages[homeNumber].addAnimal(animal);
                    break;
                }
                case "Enclosure": {
                    if (homeNumber < 0 || homeNumber >= enclosures.length) {
                        throw new Exception("Our Zoo doesn't have " + homeType + " number " + homeNumber);
                    }
                    if (enclosures[homeNumber] == null) {
                        enclosures[homeNumber] = new Enclosure();
                    }
                    //check if Animal is allowed in Residence
                    animal.checkAnimalInResidence(homeType, enclosures[homeNumber].getAnimals());
                    enclosures[homeNumber].addAnimal(animal);
                    break;
                }
                default: {
                    throw new Exception("Not a valid homeType");
                }
            }

            //put nickname into HashMap
            nicknameMap.put(animalNickname, animal);
            return true;
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }

    /**
     * Creates an instance of Animal by a String (the animal name)
     *
     * @param animalSpecies  The name of the animal (ClassName)
     * @param animalNickname The nickname (passed to constructor)
     * @return An instance of Animal
     * @throws Exception detailed error
     */
    private Animal createAnimal(String animalSpecies, String animalNickname) throws Exception {
        String currentClass = this.getClass().getName();
        String currentPackage = currentClass.substring(0, currentClass.lastIndexOf('.') + 1);
        try {
            return (Animal) Class
                .forName(currentPackage + animalSpecies)
                .getConstructor(new Class[]{String.class})
                .newInstance(new Object[]{animalNickname});
        } catch (Exception e) {
            throw new Exception(animalSpecies + " is not a defined Class of an animal!");
        }
    }

    @Override
    public boolean removeAnimal(String animalNickname) {
        try {
            Animal animal = nicknameMap.get(animalNickname);
            if (animal == null) {
                throw new Exception("No animal with nickname " + animalNickname + " found!");
            }
            Residence residence = animal.getResidence();
            if (residence == null) {
                throw new Exception("Animal with nickname " + animalNickname + " is not in any residence!");
            }
            switch (residence.getName()) {
                case "Cage": {
                    cages[Arrays.asList(cages).indexOf(residence)] = null;
                    break;
                }
                case "Enclosure": {
                    enclosures[Arrays.asList(enclosures).indexOf(residence)] = null;
                    break;
                }
                default: {
                    throw new Exception("Not a supported Residence Type");
                }
            }
            //remove animal relation to residence
            animal.setResidence(null);
            //remove animal from nicknameMap
            nicknameMap.remove(animalNickname);
            return true;
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }

    @Override
    public boolean relocateAnimal(String animalNickname, String homeType, int homeNumber) {
        try {
            Animal animal = nicknameMap.get(animalNickname);
            if (animal == null) {
                throw new Exception("No animal with nickname " + animalNickname + " found!");
            }

            //check if Animal can live in Residence
            switch (homeType) {
                case "Cage": {
                    if (homeNumber < 0 || homeNumber >= cages.length) {
                        throw new Exception("Our Zoo doesn't have " + homeType + " number " + homeNumber);
                    }
                    if (cages[homeNumber] == null) {
                        cages[homeNumber] = new Cage();
                    }
                    //check if Animal is allowed in Residence
                    animal.checkAnimalInResidence(homeType, cages[homeNumber].getAnimals());
                    break;
                }
                case "Enclosure": {
                    if (homeNumber < 0 || homeNumber >= enclosures.length) {
                        throw new Exception("Our Zoo doesn't have " + homeType + " number " + homeNumber);
                    }
                    if (enclosures[homeNumber] == null) {
                        enclosures[homeNumber] = new Enclosure();
                    }
                    //check if Animal is allowed in Residence
                    animal.checkAnimalInResidence(homeType, enclosures[homeNumber].getAnimals());
                    break;
                }
                default: {
                    throw new Exception("Not a valid homeType");
                }
            }

            //remove animal
            if (!removeAnimal(animalNickname)) {
                throw new Exception();
            }
            //add animal back to nicknameMap
            nicknameMap.put(animalNickname, animal);
            //add animal to new home
            switch (homeType) {
                case "Cage": {
                    cages[homeNumber].addAnimal(animal);
                    break;
                }
                case "Enclosure": {
                    enclosures[homeNumber].addAnimal(animal);
                    break;
                }
                default: {
                    throw new Exception("Not a valid homeType");
                }
            }

            return true;
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }

    @Override
    public boolean feedAnimals(String homeType, int homeNumber, int amountOfFood, String animalFoodType) {
        try {
            Integer foodAmount = foodAmounts.get(animalFoodType);
            if (foodAmount == null) {
                foodAmount = 0;
            }
            if (foodAmount < amountOfFood) {
                throw new Exception("Not enough " + animalFoodType + ", current amount = " + foodAmount);
            }

            //feed animals with amount
            switch (homeType) {
                case "Cage": {
                    cages[homeNumber].feedAnimals(animalFoodType);
                    break;
                }
                case "Enclosure": {
                    enclosures[homeNumber].feedAnimals(animalFoodType);
                    break;
                }
                default: {
                    throw new Exception("Not a valid homeType");
                }
            }
            //save new food amount
            foodAmount -= amountOfFood;
            foodAmounts.put(animalFoodType, foodAmount);
            return true;
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean buyFood(String animalFoodType, int amount) {
        try {
            if (amount <= 0) {
                throw new Exception("You can't buy negative amount of " + animalFoodType);
            }
            Integer foodAmount = foodAmounts.get(animalFoodType);
            if (foodAmount == null) {
                foodAmounts.put(animalFoodType, amount);
            } else {
                foodAmount += amount;
                foodAmounts.put(animalFoodType, foodAmount);
            }
            return true;
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }

    @Override
    public void listOfTenants() {
        for (int i = 0; i < cages.length; i++) {
            if (cages[i] != null && cages[i].getAnimalsAmount() > 0) {
                //change index to number
                System.out.println("Cage " + i);
                cages[i].printAnimals();
            }
        }

        for (int i = 0; i < enclosures.length; i++) {
            if (enclosures[i] != null && enclosures[i].getAnimalsAmount() > 0) {
                //change index to number
                System.out.println("Enclosure " + i);
                enclosures[i].printAnimals();
            }
        }
    }
}