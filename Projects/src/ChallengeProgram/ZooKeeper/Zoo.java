package ChallengeProgram.ZooKeeper;

/**
 * Interface Zoo for zoo management tasks. Challenge Program 2018 assignment "Zoo Keeper".
 */

public interface Zoo {
    /*  Accomodates an animal in a particular home in the zoo.
     *  @param animalSpecies the name of the species of an animal to accomodate
     *  @param animalNickname the nickname of the species of an animal to accomodate
     *  @param homeType the type of the home in the zoo to accomodate the animal
     *  @param homeNumber the number of the home in the zoo to accomodate the animal
     *  @return true, if accomadation is successful, otherwise false
     */
    boolean accomodateAnimal(String animalSpecies, String animalNickname, String homeType, int homeNumber);

    /* Removes an animal from the zoo.
     *  @param animalNickname the nickname of the species of an animal to accomodate
     *  @return true, if removal is successful, otherwise false
     */
    boolean removeAnimal(String animalNickname);

    /*  Relocates an animal to a new home in the zoo.
     *  @param animalNickname the nickname of the species of an animal to accomodate
     *  @param homeType the type of the home in the zoo to relocate the animal
     *  @param homeNumber the number of the home in the zoo to accomodate the animal
     *  @return true, if relocation is successful, otherwise false
     */
    boolean relocateAnimal(String animalNickname, String homeType, int homeNumber);

    /*  Feeds all animals in a particular home in the zoo.
     *  @param homeType the type of the home in the zoo to feed the animal
     *  @param homeNumber the number of the home in the zoo to feed the animals
     *  @param amountOfFood amount of food to feed the animals
     *  @param animalFoodType type of  food to feed the animals
     *  @return true, if feeding is successful, otherwise false
     */
    boolean feedAnimals(String homeType, int homeNumber, int amountOfFood, String animalFoodType);

    /*  Replenishes a particular food store in the zoo.
     *  @param animalFoodType type of food to feed the animals
     *  @param amount amount of food to feed the animals
     *  @return true, if feeding is successful, otherwise false
     */
    boolean buyFood(String animalFoodType, int amount);

    /* Prints to the System console the lists of all animals with their nicknames
     * living in each in all non emlty animal home in the zoo.
     */
    void listOfTenants();

}
