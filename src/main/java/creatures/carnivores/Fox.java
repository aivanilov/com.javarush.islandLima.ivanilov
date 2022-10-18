package creatures.carnivores;

import annotations.AnimalScanner;
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
public class Fox extends Carnivore {

    public Fox() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Лиса");
        setIcon("\uD83E\uDD8A");
        AnimalLimits animalLimits = new AnimalLimits(
                3,
                6,
                8,
                30,
                2,
                2,
                0.5);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                8,
                3,
                6);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Rabbit.class, 70);
        foodPreferences.put(Mouse.class, 90);
        foodPreferences.put(Duck.class, 60);
        foodPreferences.put(Caterpillar.class, 40);
        setFoodPreferences(foodPreferences);
    }
}
