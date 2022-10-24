package entities;

import game.GameField;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameInfo {
    Stats stats;
    GameField gameField;
}
