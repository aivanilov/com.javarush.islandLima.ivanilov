package creatures.carnivores;

import actions.Nesting;
import annotations.AnimalScanner;
import creatures.Plant;
import creatures.herbivores.Caterpillar;
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
public class Duck extends Carnivore implements Nesting {

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
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                4,
                5,
                15);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Caterpillar.class, 90);
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);
    }
}
