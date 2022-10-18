package creatures.herbivores;

import annotations.AnimalScanner;
import creatures.Plant;
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
public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Гусеница");
        setIcon("\uD83D\uDC1B");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                0.005,
                0.01,
                1000,
                0,
                0.05,
                0.025);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                2,
                25,
                150);
        setBreedingParameters(breedingParameters);
    }
}
