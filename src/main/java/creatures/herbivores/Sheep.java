package creatures.herbivores;

import builders.AnimalScanner;
import entities.AnimalLimits;
import entities.BreedingParams;
import entities.Terrain;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@AnimalScanner
@Getter
@Setter
@SuppressWarnings("unused")
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
        BreedingParams breedingParams = new BreedingParams(
                21,
                1,
                3);
        setBreedingParams(breedingParams);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
