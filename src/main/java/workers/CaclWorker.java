package workers;

import creatures.Animal;
import game.Cell;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;


public class CaclWorker implements Callable<Map<Type, Long>> {

    private final Cell cell;

    public CaclWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Map<Type, Long> call() {
        Map<Type, Long> calc = new HashMap<>();
        Map<Type, Set<Animal>> allAnimals = cell.getAnimals();
        for (var entry : allAnimals.entrySet()) {
            Type animalType = entry.getKey();
            long numberOfAnimals = entry.getValue().size();
            calc.put(animalType, numberOfAnimals);
        }
        double plantMass = cell.getPlants().getMass();
        calc.put(cell.getPlants().getClass(), Math.round(plantMass));

        return calc;
    }
}
