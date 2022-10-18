package creatures.herbivores;

import annotations.AnimalScanner;
import entities.AnimalLimits;
import entities.BreedingParameters;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
public class Horse extends Herbivore {

    public Horse() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Лошадь");
        setIcon("\uD83D\uDC0E");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                340,
                400,
                20,
                4,
                60,
                20);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                50,
                1,
                2);
        setBreedingParameters(breedingParameters);
    }
}
