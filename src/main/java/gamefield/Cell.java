package gamefield;

import creatures.Animal;
import creatures.Plant;
import entities.Direction;
import entities.Territory;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Cell {
    private GameField gameField;
    private int row;
    private int column;
    private Map<Direction, Cell> surroundings;
    private Territory territory;
    private Map<Type, Set<Animal>> animals;
    private Plant plants;

    public Cell(GameField gameField, Territory territory, Map<Type, Set<Animal>> animals, Plant plants) {
        this.gameField = gameField;
        this.territory = territory;
        this.animals = animals;
        this.plants = plants;
    }

    public void identifySurroundings(){
        Map<Direction, Cell> surroundings = new HashMap<>();
        findNorth();
        this.surroundings = surroundings;
    }

    private void findNorth() {
    }
}
