package workerManagers;

import workers.Game;
import utils.Waiter;
import workers.DirectionsWorker;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class DirectionsManager extends Manager {

    public DirectionsManager(Game game) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.gameField = game.getGameField();
    }

    @Override
    public void run() {
        Arrays.stream(gameField
                        .getRealm())
                        .forEach(row -> Arrays.stream(row)
                        .forEach(cell -> executorService.submit(new DirectionsWorker(cell, gameField))));
        executorService.shutdown();
        Waiter.awaitTermination(executorService);
    }
}
