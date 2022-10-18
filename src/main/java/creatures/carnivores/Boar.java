package creatures.carnivores;

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
public class Boar extends Carnivore {

    public Boar() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Кабан");
        setIcon("\uD83D\uDC17");
        AnimalLimits animalLimits = new AnimalLimits(
                3,
                350,
                400,
                50,
                2,
                50,
                25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                18,
                2,
                8);
        setBreedingParameters(breedingParameters);
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Mouse.class, 50);
        foodPreferences.put(Caterpillar.class, 90);
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);
    }
}
