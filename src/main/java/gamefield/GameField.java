package gamefield;

import builders.AnimalBuilder;
import builders.CellBuilder;
import exceptions.IslandGameException;
import lombok.Getter;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Getter
public class GameField {
    private final Cell[][] realm;
    private final AnimalBuilder animalBuilder;
    private int rows;
    private int columns;

    public GameField(int rows, int columns){
        this.animalBuilder = new AnimalBuilder();
        this.realm = generateGameField(rows, columns);
        this.rows = rows;
        this.columns = columns;
    }

    public Cell[][] generateGameField(int rows, int colums) {
        if (rows <= 0 || colums <= 0)
            throw new IslandGameException("Game field has no rows or columns");

        Cell[][] realm = new Cell[rows][colums];
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
        CellBuilder cellBuilder = new CellBuilder(this, this.animalBuilder);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                try {
                    Future<Cell> cell = executor.submit(cellBuilder);
                    realm[i][j] = cell.get();
                    realm[i][j].setRow(i);
                    realm[i][j].setColumn(j);
                } catch (Exception e) {
                    throw new IslandGameException(e.getCause());
                }
            }
        }
        executor.shutdown();
        return realm;
    }

    @Override
    public String toString() {
        return "Here's gonna be a game field";
        //TODO nice looking field for console
    }
}
