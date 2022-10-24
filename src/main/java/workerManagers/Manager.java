package workerManagers;

import workers.Game;
import game.GameField;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;

@Getter
@Setter
public abstract class Manager implements Runnable {
    public static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    protected ExecutorService executorService;
    protected Game game;
    protected GameField gameField;
}
