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
public class Boa extends Carnivore {

    public Boa() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Удав");
        setIcon("\uD83D\uDC0D");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                12,
                15,
                30,
                1,
                3,
                0.25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                17,
                5,
                50);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Fox.class, 15);
        foodPreferences.put(Rabbit.class, 20);
        foodPreferences.put(Mouse.class, 40);
        foodPreferences.put(Duck.class, 10);
        setFoodPreferences(foodPreferences);
    }
}
