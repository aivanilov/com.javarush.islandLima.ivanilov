package creatures.carnivores;

import actions.Nesting;
import annotations.AnimalScanner;
import creatures.Plant;
import creatures.herbivores.Caterpillar;
import creatures.herbivores.Rabbit;
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
public class Eagle extends Carnivore implements Nesting {

    public Eagle() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Орёл");
        setIcon("\uD83E\uDD85");
        AnimalLimits animalLimits = new AnimalLimits(
                6,
                5,
                6,
                20,
                3,
                1,
                0.5);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                11,
                1,
                3);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Fox.class, 10);
        foodPreferences.put(Rabbit.class, 90);
        foodPreferences.put(Mouse.class, 90);
        foodPreferences.put(Duck.class, 80);
        setFoodPreferences(foodPreferences);
    }
}
