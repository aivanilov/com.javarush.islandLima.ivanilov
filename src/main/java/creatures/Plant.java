package creatures;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plant extends Creature {

    public static final int maxMassInCell = 4000; //TODO extract parameter
    private double mass;

    public Plant(int mass) {
        this.mass = mass;
        setIcon("\uD83C\uDF33");
    }

    @Override
    public void reproduce() {
    }
}
