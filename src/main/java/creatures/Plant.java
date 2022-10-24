package creatures;

import exceptions.IslandGameException;
import game.Settings;
import lombok.Getter;
import lombok.Setter;
import utils.Dice;

@Getter
@Setter
public class Plant extends Creature {

    public static final int maxMassInCell = Settings.MAX_PLANT_MASS_IN_CELL;
    private double mass;

    public Plant(int mass) {
        this.mass = mass;
        setIcon("\uD83C\uDF32");
    }

    @Override
    public void reproduce(Creature creature) {
        if (creature == this) {
            double currentMass = this.getMass();
            double growthCoefficient = Settings.PLANT_GROWTH_TEMPO;
            this.setMass(currentMass * growthCoefficient);
        } else {
            throw new IslandGameException("Target object to reproduce a plant must be \"this\"");
        }
    }
}
