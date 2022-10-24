import game.Settings;
import views.ConsoleView;
import workers.Game;

public class  Runner {

    public static void main(String[] args) {
        Game game = new Game(Settings.ROWS, Settings.COLUMNS);
        ConsoleView consoleView = new ConsoleView(game);
        consoleView.runGame();
    }
}