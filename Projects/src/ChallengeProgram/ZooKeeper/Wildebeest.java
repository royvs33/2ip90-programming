package ChallengeProgram.ZooKeeper;

import java.util.ArrayList;

public class Wildebeest extends Grasseater {
    public Wildebeest(String nickName) {
        super(nickName);
        setAllowedFood("Hey", "Corn", "Grain");
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
