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
public class Rabbit extends Herbivore {

    public Rabbit() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Кролик");
        setIcon("\uD83D\uDC07");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                1.55,
                2,
                150,
                2,
                0.45,
                0.3);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                4,
                4,
                12);
        setBreedingParameters(breedingParameters);
    }
}
