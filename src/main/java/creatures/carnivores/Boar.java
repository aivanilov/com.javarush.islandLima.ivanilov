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
public class Boar extends Carnivore {

    public Boar() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Кабан");
        setIcon("\uD83D\uDC17");
        AnimalLimits animalLimits = new AnimalLimits(
                5,
                350,
                400,
                50,
                2,
                50,
                25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);

        BreedingParams breedingParams = new BreedingParams(
                18,
                2,
                8);
        setBreedingParams(breedingParams);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Mouse.class, 50);
        foodPreferences.put(Caterpillar.class, 90);
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);

        terrains.addAll(Arrays.asList(Terrain.values()));
        getTerrains().remove(Terrain.RIVER);
    }
}
