package creatures.carnivores;

import annotations.AnimalScanner;
import creatures.herbivores.*;
import entities.AnimalLimits;
import entities.BreedingParameters;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
public class Wolf extends Carnivore {

    public Wolf() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Волк");
        setIcon("\uD83D\uDC3A");
        AnimalLimits animalLimits = new AnimalLimits(
                3,
                42,
                50,
                30,
                3,
                8,
                4);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                10,
                2,
                6);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Horse.class, 10);
        foodPreferences.put(Deer.class, 15);
        foodPreferences.put(Rabbit.class, 60);
        foodPreferences.put(Mouse.class, 80);
        foodPreferences.put(Goat.class, 60);
        foodPreferences.put(Sheep.class, 70);
        foodPreferences.put(Boar.class, 15);
        foodPreferences.put(Buffalo.class, 10);
        foodPreferences.put(Duck.class, 40);
        setFoodPreferences(foodPreferences);
    }
}
