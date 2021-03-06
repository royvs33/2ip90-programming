package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;

public class Leopard extends Carnivore {
    public Leopard(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void checkAnimalInResidence(String homeType, ArrayList<Animal> residenceAnimals) throws Exception {
        //there should be no animals in the residence, because Leopard is lonely
        if (residenceAnimals.size() > 0) {
            throw new Exception(getName() + " can't live with " + residenceAnimals.get(0).getName());
        }
        //default behavoir check not needed
    }
}
