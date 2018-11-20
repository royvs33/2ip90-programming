package ChallengeProgram.ZooKeeper;

public class Enclosure extends Residence {
    public Enclosure() {
        super(20);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
