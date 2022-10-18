package views;

import entities.GameInfo;
import gamefield.GameField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import utils.Sleeper;
import workers.Game;

import java.util.Optional;


@AllArgsConstructor
@Getter
public class ConsoleView implements View {
    Game game;

    @Override
    public void runGame() {
        game.initialize();
        while (!game.isStopped()) {
            Optional<GameInfo> gameInfo = game.doIteration();
            printSummary(gameInfo);
            printField(game.getGameField());
            Sleeper.sleep(500);
        }
    }

    private void printField(GameField gameField) {
        System.out.println(gameField);
    }

    private void printSummary(Optional<GameInfo> gameInfo) {
        System.out.println(gameInfo.get().getStats());
    }
}
