import utils.Dice;
import views.ConsoleView;
import workers.Game;

import java.util.concurrent.ThreadLocalRandom;

public class  Runner {
    public static void main(String[] args) {
        Game game = new Game(100,20);
        ConsoleView consoleView = new ConsoleView(game);
        consoleView.runGame();
    }
}
