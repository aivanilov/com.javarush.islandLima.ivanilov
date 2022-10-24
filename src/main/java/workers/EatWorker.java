package workers;

import behaviours.EatingCarrion;
import creatures.Animal;
import creatures.Creature;
import creatures.Plant;
import game.Cell;
import game.Settings;
import utils.Dice;

import java.lang.reflect.Type;
import java.util.*;

public class EatWorker implements Runnable {
    private final Cell cell;

    public EatWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        eatingPhase();
        clearDeadAnimals();
    }

    private void clearDeadAnimals() {
        cell.getAnimals()
                .forEach((type, animalSet) -> animalSet
                        .removeIf(animal -> animal.getWeight() <= animal.getAnimalLimits().getMinWeight()));
    }

    private void eatingPhase() {
            Map<Type, Set<Animal>> animals = cell.getAnimals();
            for (var entry : animals.entrySet()) {
                Set<Animal> animalSet = entry.getValue();
                for (var animal : animalSet) {
                    animal.starve();

                    if (animal.getWeight() == animal.getAnimalLimits().getMaxWeight())
                        continue;

                    int tryouts = animal.getAnimalLimits().getEatingAttempts();
                    boolean successHunt = false;
                    while ( tryouts > 0) {
                        Creature target = peekTarget(cell, animal);
                        if (target != null) {
                            double successRate = Dice.random(0, 100.0);
                            double minimalSuccessRate = 100 - (double) animal.getFoodPreferences().get(target.getClass());
                            if (successRate > minimalSuccessRate) {
                                animal.getLock().lock();
                                target.getLock().lock();
                                animal.eat(target, cell);
                                successHunt = true;
                                target.getLock().unlock();
                                animal.getLock().unlock();
                            }
                        }
                        tryouts--;
                    }

                    if (!successHunt && animal.getClass().isAnnotationPresent(EatingCarrion.class)) {
                        animal.eatCarrion(cell);
                    }
                }
                decreaseCarrion();
            }
    }

    private void decreaseCarrion() {
        double carrion = this.cell.getCarrion();
        cell.setCarrion(carrion / Settings.CARRION_DECREASE_AT_THE_END_OF_ITERATION_MULTIPLIER);
        cell.setCarrion(0);
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
