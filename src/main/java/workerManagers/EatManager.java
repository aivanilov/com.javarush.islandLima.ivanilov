package workerManagers;

import workers.Game;
import utils.Waiter;
import workers.EatWorker;

import java.util.Arrays;
import java.util.concurrent.Executors;

public class EatManager extends Manager {


    public EatManager(Game game) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.gameField = game.getGameField();
    }

    @Override
    public void run() {
        Arrays.stream(gameField.getRealm())
                .forEach(cells -> Arrays.stream(cells)
                        .forEach(cell -> {
            EatWorker eatWorker = new EatWorker(cell);
            executorService.submit(eatWorker);
        }));
        executorService.shutdown();
        Waiter.awaitTermination(executorService);
    }
}
