package builders;

import creatures.Animal;
import creatures.Plant;
import entities.Terrain;
import exceptions.IslandGameException;
import game.Cell;
import game.GameField;
import utils.Dice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class CellBuilder implements Callable<Cell> {
    private final GameField gameField;
    private final Map<Type, Object> prototypes;

    public CellBuilder(GameField gameField, AnimalBuilder animalBuilder) {
        this.gameField = gameField;
        prototypes = animalBuilder.getPrototypes();
    }

    @Override
    public Cell call() {
        return generateRandomlyFilledCell();
    }

    public Cell generateRandomlyFilledCell() {
        Map<Type, Set<Animal>> animals = new HashMap<>();

        for (var entry : prototypes.entrySet()) {
            Animal animal = (Animal) entry.getValue();
            Set<Animal> generatedAnimals = generateAnimals(animal.getClass(), prototypes);
            animals.put(animal.getClass(), generatedAnimals);
        }

        Plant plants = generatePlants();
        return new Cell(gameField, Terrain.generateTerritory(), animals, plants);
    }

    private Plant generatePlants() {
        return new Plant(Dice.random(0, Plant.maxMassInCell));
    }

    private Set<Animal> generateAnimals(Class<? extends Animal> animalClass, Map<Type, Object> prototypes) {
        Set<Animal> result = new HashSet<>();
        Animal animal = (Animal) prototypes.get(animalClass);
        double probabilityOfAnimalSpawn = Dice.random(0, 1.0); //TODO extract constant
        boolean isSpawned = probabilityOfAnimalSpawn > 0.25; //TODO extract constant
        if (isSpawned) {
            int animalAmount = Dice.random(0, animal.getAnimalLimits().getMaxPopulationInCell());
            for (int i = 0; i < animalAmount; i++) {
                try {
                    Animal clone = animal.clone();
                    result.add(clone);
                } catch (Exception e) {
                    throw new IslandGameException(e);
                }
            }
        }
        return result;
    }
}