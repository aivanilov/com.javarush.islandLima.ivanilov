package entities;

import gamefield.GameField;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameInfo {
    Stats stats;
    GameField gameField;
}
