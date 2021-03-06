package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;

public class Zebra extends Grasseater {
    public Zebra(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals) throws Exception {
        super.checkAnimalInResidence(homeType, residenceAnimals, getName());
    }
}
