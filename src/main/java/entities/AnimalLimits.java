package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Getter
@Setter
public class AnimalLimits {
    private int eatingAttempts;
    private double minWeight;
    private double maxWeight;
    private int maxPopulationInCell;
    private int speed;
    private double amountOfFoodNeeded;
    private double starvingTempo;

    public AnimalLimits() {
    }

    @Override
    public String toString() {
        return "AnimalLimits{" +
                "eatingAttempts=" + eatingAttempts +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", maxPopulationInCell=" + maxPopulationInCell +
                ", speed=" + speed +
                ", amountOfFoodNeeded=" + amountOfFoodNeeded +
                ", starvingTempo=" + starvingTempo +
                '}';
    }
}