package creatures.carnivores;

import annotations.AnimalScanner;
import creatures.herbivores.*;
import entities.AnimalLimits;
import entities.BreedingParameters;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
public class Bear extends Carnivore {

    //TODO import params

    public Bear() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Медведь");
        setIcon("\uD83D\uDC3B");
        AnimalLimits animalLimits = new AnimalLimits(
                2,
                420,
                500,
                5,
                2,
                80,
                20);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                        determineGender(),
                        false,
                        0,
                        28,
                        1,
                        3);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Boa.class, 80);
        foodPreferences.put(Horse.class, 40);
        foodPreferences.put(Deer.class, 80);
        foodPreferences.put(Rabbit.class, 80);
        foodPreferences.put(Mouse.class, 90);
        foodPreferences.put(Goat.class, 70);
        foodPreferences.put(Sheep.class, 70);
        foodPreferences.put(Boar.class, 50);
        foodPreferences.put(Buffalo.class, 20);
        foodPreferences.put(Duck.class, 10);
        setFoodPreferences(foodPreferences);
    }
}
