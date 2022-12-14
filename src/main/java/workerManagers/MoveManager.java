package workerManagers;

import workers.Game;
import utils.Waiter;
import workers.MoveWorker;

import java.util.Arrays;
import java.util.concurrent.Executors;

public class MoveManager extends Manager {

    public MoveManager(Game game) {
        this.executorService = Executors.newSingleThreadExecutor();
        this.gameField = game.getGameField();
    }

    @Override
    public void run() {
        Arrays.stream(gameField.getRealm())
                        .forEach(cells -> Arrays.stream(cells)
                        .forEach(cell -> {
            MoveWorker moveWorker = new MoveWorker(cell);
            executorService.submit(moveWorker);
        }));
        executorService.shutdown();
        Waiter.awaitTermination(executorService);
    }
}
