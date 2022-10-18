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
public class Deer extends Herbivore {

    public Deer() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Олень");
        setIcon("\uD83E\uDD8C");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                250,
                300,
                20,
                4,
                50,
                25);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                34,
                1,
                5);
        setBreedingParameters(breedingParameters);
    }
}
