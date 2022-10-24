package game;

import creatures.Animal;
import creatures.Plant;
import entities.Direction;
import entities.Terrain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@EqualsAndHashCode
public class Cell {
    private volatile GameField gameField;
    private int row;
    private int column;
    private volatile Map<Direction, Cell> surroundings;
    private Terrain terrain;
    private volatile Map<Type, Set<Animal>> animals;
    private Plant plants;
    private ReentrantLock lock = new ReentrantLock();
    private double carrion = 0;

    public Cell(GameField gameField, Terrain terrain, Map<Type, Set<Animal>> animals, Plant plants) {
        this.gameField = gameField;
        this.terrain = terrain;
        this.animals = animals;
        this.plants = plants;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
