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
public class Buffalo extends Herbivore {

    public Buffalo() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Буйвол");
        setIcon("\uD83D\uDC03");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                600,
                700,
                10,
                3,
                100,
                75);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                45,
                1,
                1);
        setBreedingParameters(breedingParameters);
    }
}
