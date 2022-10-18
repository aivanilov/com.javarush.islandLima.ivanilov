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
import java.util.concurrent.*;

@Getter
@Setter
public class CalcManager implements Runnable {

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService;
    private final GameField gameField;
    private final Game game;
    private Stats stats;

    public CalcManager(Game game) {
        this.game = game;
        this.gameField = game.getGameField();
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
    }

    @Override
    public void run() {
        Map<Type, Long> numberOfCreatures = new HashMap<>();
        List<Callable<Map<Type, Long>>> callables = new LinkedList<>();

        for (int i = 0; i < gameField.getRows(); i++) {
            Callable<Map<Type, Long>> calcByRowWorker = new CaclWorker(gameField.getRealm()[i]);
            callables.add(calcByRowWorker);
        }

        List<Future<Map<Type, Long>>>  workers = runCalculations(callables);

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
        stats = new Stats(gameField, numberOfCreatures, game.getIteration());
        executorService.shutdown();
    }

    private List<Future<Map<Type, Long>>> runCalculations(List<? extends Callable<Map<Type, Long>>>  workers) {
        List<Callable<Map<Type, Long>>> futureTasks = new LinkedList<>();

        for (int i = 0; i < gameField.getRows(); i++) {
            CaclWorker caclWorker = new CaclWorker(gameField.getRealm()[i]);
            futureTasks.add(caclWorker);
        }

        try{
            return executorService.invokeAll(futureTasks);
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }

    }
}
