package creatures.carnivores;

import behaviours.Nesting;
import builders.AnimalScanner;
import creatures.Plant;
import creatures.herbivores.Caterpillar;
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
public class Duck extends Carnivore {

    public Duck() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Утка");
        setIcon("\uD83E\uDD86");
        AnimalLimits animalLimits = new AnimalLimits(
                10,
                0.85,
                1,
                200,
                4,
                0.15,
                0.15);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                4,
                5,
                15);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Caterpillar.class, 90);
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
        getTerrains().remove(Terrain.MOUNTAINS);
    }
}
