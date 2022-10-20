package workers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EatManager implements Runnable {

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService;
    private final Game game;

    public EatManager(Game game) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.game = game;
    }

    @Override
    public void run() {
        for (int i = 0; i < game.getGameField().getRows(); i++) {
            EatWorker eatWorker = new EatWorker(game.getGameField().getRealm()[i]);
            executorService.submit(eatWorker);
        }
        executorService.shutdown();
    }
}
