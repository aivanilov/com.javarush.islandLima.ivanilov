package workers;

import builders.AnimalBuilder;
import entities.GameInfo;
import exceptions.IslandGameException;
import gamefield.GameField;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.concurrent.*;

@Getter
@Setter
public class Game implements Runnable {
    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();

    private GameField gameField;
    private boolean isStopped; //TODO stop criteria
    private int rows;
    private int columns;
    private final ExecutorService executorService;
    private AnimalBuilder animalBuilder;

    public Game(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        executorService = Executors.newFixedThreadPool(availableProcessors);
    }

    @Override
    public void run() {
        this.initialize();
    }

    public void initialize() {
        isStopped = false;
        gameField = new GameField(rows, columns);
    }

    public Optional<GameInfo> doIteration() { //TODO Implement checking of stop criteria
        if (!isStopped) {
            EatingManager eatingManager = new EatingManager(gameField);
            runAndJoin(eatingManager);
            CalcManager calcManager = new CalcManager(gameField);
            runAndJoin(calcManager);
            return Optional.of(new GameInfo(calcManager.getStats(), gameField));
        }
        return Optional.empty();
    }

    private void runAndJoin(Runnable runnable) {
        Thread eating = new Thread(runnable);
        eating.start();
        try{
            eating.join();
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }
    }
}
