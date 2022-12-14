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
        BreedingParams breedingParams = new BreedingParams(
                25,
                1,
                6);
        setBreedingParams(breedingParams);

        terrains.addAll(Arrays.asList(Terrain.values()));
    }
}
