package views;

import entities.GameInfo;
import gamefield.GameField;
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
        game.initialize();
        while (!game.isStopped()) {
            GameInfo gameInfo = game.doIteration();
            printSummary(gameInfo);
            printField(game.getGameField());
            Sleeper.sleep(500);
        }
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
