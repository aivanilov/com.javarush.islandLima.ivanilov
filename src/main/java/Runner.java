import views.ConsoleView;
import workers.Game;

public class  Runner {
    public static void main(String[] args) {
        Game game = new Game(10,10); //TODO extract to config
        ConsoleView consoleView = new ConsoleView(game);
        consoleView.runGame();
    }
}