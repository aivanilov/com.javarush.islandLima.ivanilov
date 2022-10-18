package workers;

import exceptions.IslandGameException;
import gamefield.GameField;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EatingManager implements Runnable {

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService;
    private final Game game;

    public EatingManager(Game game) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.game = game;
    }

    @Override
    public void run() {
        for (int i = 0; i < game.getGameField().getRows(); i++) {
            EatingWorker eatingWorker = new EatingWorker(game.getGameField().getRealm()[i]);
            executorService.submit(eatingWorker);
        }
        executorService.shutdown();
    }
}
