package workers;

import exceptions.IslandGameException;
import gamefield.GameField;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EatingManager implements Runnable {

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService;
    private final GameField gameField;

    public EatingManager(GameField gameField) {
        this.executorService = Executors.newFixedThreadPool(availableProcessors);
        this.gameField = gameField;
    }

    @Override
    public void run() {
        LinkedList<Callable<Boolean>> listOfRows = new LinkedList<>();

        for (int i = 0; i < gameField.getRealm().length; i++) {
            EatingWorker eatingWorker = new EatingWorker(gameField.getRealm()[i]);
            listOfRows.add(eatingWorker);
        }

        try {
            executorService.invokeAll(listOfRows);
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }
    }
}
