package creatures;

import exceptions.IslandGameException;
import lombok.Getter;
import lombok.Setter;
import utils.Dice;

@Getter
@Setter
public class Plant extends Creature {

    public static final int maxMassInCell = 75000; //TODO extract parameter
    private double mass;

    public Plant(int mass) {
        this.mass = mass;
        setIcon("\uD83C\uDF32");
    }

    @Override
    public void reproduce(Creature creature) {
        if (creature == this) {
            double currentMass = this.getMass();
            double growthCoefficient = 1.03; //TODO Extract variable to config file
            this.setMass(currentMass * growthCoefficient);
        } else {
            throw new IslandGameException("Target object to reproduce a plant must be \"this\"");
        }
    }
}
