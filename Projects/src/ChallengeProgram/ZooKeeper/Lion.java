package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;

public class Lion extends Carnivore {
    public Lion(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals) throws Exception {
        //call default Carnivore behavoir
        super.checkAnimalInResidence(homeType, residenceAnimals, getName());
    }
}
