package ChallengeProgram.ZooKeeper;

public class Cage extends Residence {
    public Cage() {
        super(4);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
