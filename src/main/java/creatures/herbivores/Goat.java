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
public class Goat extends Herbivore {

    public Goat() {
        super();
        setId(idCounter.getAndIncrement());
        setName("Коза");
        setIcon("\uD83D\uDC10");
        AnimalLimits animalLimits = new AnimalLimits(
                1,
                50,
                60,
                140,
                3,
                10,
                3);
        setWeight(ThreadLocalRandom.current().nextDouble(animalLimits.getMinWeight(), animalLimits.getMaxWeight()));
        setAnimalLimits(animalLimits);
        BreedingParameters breedingParameters = new BreedingParameters(
                determineGender(),
                false,
                0,
                25,
                1,
                6);
        setBreedingParameters(breedingParameters);
    }
}
