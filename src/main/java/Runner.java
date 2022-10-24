
import game.Settings;
import views.ConsoleView;
import workers.Game;

public class  Runner {

    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.parseSettings();
        Game game = new Game(Settings.settings.ROWS, Settings.settings.COLUMNS);
        ConsoleView consoleView = new ConsoleView(game);
        consoleView.runGame();
    }
}