package creatures.carnivores;

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
@SuppressWarnings("unused")
public class Mouse extends Carnivore {

    public Mouse() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Мышь");
        setIcon("\uD83D\uDC01");
        AnimalLimits animalLimits = new AnimalLimits(
                5,
                0.04,
                0.05,
                500,
                1,
                0.01,
                0.01);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParams breedingParams = new BreedingParams(
                3,
                5,
                14);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Caterpillar.class, 90);
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
