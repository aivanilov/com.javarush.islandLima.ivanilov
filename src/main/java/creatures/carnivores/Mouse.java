package creatures.carnivores;

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
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                3,
                5,
                14);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Caterpillar.class, 90);
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);
    }
}
