package workers;

import entities.Stats;
import exceptions.IslandGameException;
import gamefield.GameField;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Getter
@Setter
public class CalcManager implements Runnable {

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService;
    private final GameField gameField;
    private Stats stats;

    public CalcManager(GameField gameField) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.gameField = gameField;
    }

    @Override
    public void run() {
        Map<Type, Long> numberOfCreatures = new HashMap<>();
        List<Callable<Map<Type, Long>>> callables = new LinkedList<>();

        for (int i = 0; i < gameField.getRows(); i++) {
            Callable<Map<Type, Long>> calcByRowWorker = new CaclWorker(gameField.getRealm()[i]);
            callables.add(calcByRowWorker);
        }

        List<FutureTask<Map<Type, Long>>>  workers = runCalculations(callables);

        try{
            for (var future: workers) {
                Map<Type, Long> creaturesInRow = future.get();
                for (var entry: creaturesInRow.entrySet()) {
                    Type type = entry.getKey();
                    Long quantity = entry.getValue();

                    if (numberOfCreatures.get(type) == null) {
                        numberOfCreatures.put(type, quantity);
                    } else {
                        Long oldQuantity = numberOfCreatures.get(type);
                        numberOfCreatures.put(
                                type,
                                oldQuantity + quantity);
                    }
                }
            }
        } catch (Exception e) {
            throw new IslandGameException(e.getCause());
        }
        stats = new Stats(gameField, numberOfCreatures);
        Stats.numberOfIterations.getAndIncrement();
    }

    private List<FutureTask<Map<Type, Long>>> runCalculations(List<? extends Callable<Map<Type, Long>>>  workers) {
        List<FutureTask<Map<Type, Long>>> futureTasks = new LinkedList<>();

        for (int i = 0; i < gameField.getRows(); i++) {
            CaclWorker caclWorker = new CaclWorker(gameField.getRealm()[i]);
            FutureTask<Map<Type, Long>> task = new FutureTask<>(caclWorker);
            futureTasks.add(task);
        }

        for (var task: futureTasks) {
            executorService.submit(task);
        }

        return futureTasks;
    }
}
