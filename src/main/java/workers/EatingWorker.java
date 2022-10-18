package workers;

import creatures.Animal;
import creatures.Creature;
import creatures.Plant;
import gamefield.Cell;
import utils.Dice;

import java.lang.reflect.Type;
import java.util.*;

public class EatingWorker implements Runnable {
    private final Cell[] row;

    public EatingWorker(Cell[] row) {
        this.row = row;
    }

    @Override
    public void run() {
        eatingPhase();
        clearDeadAnimals();
    }

    private void clearDeadAnimals() {
        for (var cell : row) {
            Map<Type, Set<Animal>> animals = cell.getAnimals();
            for (var entry : animals.entrySet()) {
                Set<Animal> animalSet = entry.getValue();
                animalSet.removeIf(animal -> animal.getWeight() < animal.getAnimalLimits().getMinWeight());
            }
        }
    }

    private void eatingPhase() {
        for (var cell : row) {
            Map<Type, Set<Animal>> animals = cell.getAnimals();
            for (var entry : animals.entrySet()) {
                Set<Animal> animalSet = entry.getValue();
                for (var animal : animalSet) {
                    if (animal.getWeight() == animal.getAnimalLimits().getMaxWeight())
                        continue;

                    int tryouts = animal.getAnimalLimits().getEatingAttempts();
                    while ( tryouts > 0) {
                        Creature target = peekTarget(cell, animal);
                        if (target != null) {
                            animal.eat(target);
                        }
                        tryouts--;
                    }
                }
            }
        }
    }

    private Creature peekTarget(Cell cell, Animal animal) {
        if (animal.getFoodPreferences().containsKey(Plant.class)) {
            double dice = Dice.random();
            if (dice <= (1.0 / animal.getFoodPreferences().size()))
                return cell.getPlants();
        }

        Set<Type> possibleTargets = animal.getFoodPreferences().keySet();
        Type possibleTarget = Dice.getRandomSetElement(possibleTargets);
        if (cell.getAnimals().containsKey(possibleTarget)) {
            Set<Animal> targets = cell.getAnimals().get(possibleTarget);
            return Dice.getRandomSetElement(targets);
        }

        return null;
    }
}
