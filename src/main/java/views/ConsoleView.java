package views;

import entities.GameInfo;
import exceptions.IslandGameException;
import game.GameField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import utils.Sleeper;
import workers.Game;


@AllArgsConstructor
@Getter
public class ConsoleView implements View {
    Game game;

    @Override
    public void runGame() {
        Thread thread = new Thread(game);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }

        while (!game.isStopped()) {
            GameInfo gameInfo = game.doIteration();
            printSummary(gameInfo);
            System.out.println("Legend for each cell: the most popular creature \\ plants. Sign \"-\" means no creatures left in the cell \n" +
                                "More details are provided for a field with 10 or less columns."); //TODO extract variable
            printField(game.getGameField());
            System.out.println();
            Sleeper.sleep();
        }
        System.out.println("All animals are dead. \n" +
                            "Game over.");
    }

    private void printField(GameField gameField) {
        System.out.println(gameField.printField());
    }

    private void printSummary(GameInfo gameInfo) {
        if (gameInfo == null) {
            System.out.println("No game stats available"); //TODO extract variable
        } else {
            System.out.println(gameInfo.getStats());
        }
    }
}
