package creatures.carnivores;

import behaviours.Nesting;
import builders.AnimalScanner;
import creatures.herbivores.Rabbit;
import entities.AnimalLimits;
import entities.BreedingParams;
import entities.Terrain;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
@Nesting
@SuppressWarnings("unused")
public class Eagle extends Carnivore {

    public Eagle() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Орёл");
        setIcon("\uD83E\uDD85");
        AnimalLimits animalLimits = new AnimalLimits(
                4,
                5,
                6,
                20,
                3,
                1,
                0.25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                11,
                1,
                3);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Fox.class, 10);
        foodPreferences.put(Rabbit.class, 90);
        foodPreferences.put(Mouse.class, 90);
        foodPreferences.put(Duck.class, 80);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
