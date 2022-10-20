package creatures;

import actions.Eating;
import actions.Movable;
import entities.AnimalLimits;
import entities.BreedingParameters;
import exceptions.IslandGameException;
import lombok.Getter;
import lombok.Setter;
import utils.Dice;

import java.lang.reflect.Type;
import java.util.Map;


@Getter
@Setter
public abstract class Animal extends Creature
        implements Eating, Movable, Cloneable {
    private String name;
    private double weight;
    private Map<Type, Integer> foodPreferences;
    private AnimalLimits animalLimits;
    private BreedingParameters breedingParameters;

    @Override
    public void reproduce() {
        //TODO implement reproduce()
    }

    @Override
    public void move() {

    }

    protected boolean determineGender() {
        double random = Dice.random();
        return random <= 0.5;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String toStringFull() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", AnimmalLimits{" + getAnimalLimits().toString() +
                ", BreedingParameters{" + getBreedingParameters().toString() +
                '}';
    }

    @Override
    public Animal clone() {
        try {
            Animal animal = (Animal) super.clone();
            int id = Animal.idCounter.getAndIncrement();
            animal.setId(id);
            String name = animal.getName();
            animal.setName(name + id);
            double minWeight = animal.getAnimalLimits().getMinWeight();
            double amountOfFoodNeeded = animal.getAnimalLimits().getAmountOfFoodNeeded();
            animal.setWeight(minWeight + Dice.random(0, amountOfFoodNeeded));
            return animal;
        } catch (CloneNotSupportedException e) {
            throw new IslandGameException(e);
        }
    }

    @Override
    public void eat(Creature creature) {
        boolean isEatable = getFoodPreferences().containsKey(creature.getClass());
        if (isEatable) {
            if (creature instanceof Plant plant) {
                eatPlant(plant);
                return;
            }

            if (creature instanceof Animal animal) {
                eatAnimal(animal);
            }
        }
    }

    private void eatAnimal(Animal animal) {
        double weight = this.getWeight();
        double maxWeight = this.getAnimalLimits().getMaxWeight();
        double amountOfFoodNeeded = maxWeight - weight;
        double targetWeight = animal.getWeight();

        if (targetWeight > amountOfFoodNeeded) {
            animal.setWeight(0);
            this.setWeight(weight + amountOfFoodNeeded);
        } else {
            if (targetWeight > 0) {
                animal.setWeight(0);
                this.setWeight(weight + targetWeight);
            }
        }
    }

    private void eatPlant(Plant plant) {
        double mass = plant.getMass();
        double weight = this.getWeight();
        double maxWeight = this.getAnimalLimits().getMaxWeight();
        double amountOfFoodNeeded = maxWeight - weight;

        if (mass > amountOfFoodNeeded) {
            plant.setMass(mass - amountOfFoodNeeded);
            this.setWeight(weight + amountOfFoodNeeded);
        } else {
            if (mass > 0) {
                plant.setMass(0);
                this.setWeight(weight + mass);
            }
        }
    }

    public void starve() {
        double weight = this.getWeight();
        double starvingTempo = this.getAnimalLimits().getStarvingTempo();
        this.setWeight(weight - starvingTempo);
    }
}
