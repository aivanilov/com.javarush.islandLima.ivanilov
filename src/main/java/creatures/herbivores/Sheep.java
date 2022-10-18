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
public class Sheep extends Herbivore {

    public Sheep() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Овца");
        setIcon("\uD83D\uDC11");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                55,
                70,
                140,
                3,
                15,
                5);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                21,
                1,
                3);
        setBreedingParameters(breedingParameters);
    }
}
