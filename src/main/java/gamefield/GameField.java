package gamefield;

import builders.AnimalBuilder;
import builders.CellBuilder;
import creatures.Animal;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.document.TableRowStyle;
import de.vandermeer.skb.interfaces.document.TableRowType;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import exceptions.IslandGameException;
import lombok.Getter;

import javax.swing.text.TableView;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.*;

@Getter
public class GameField {
    static int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final Cell[][] realm;
    private final AnimalBuilder animalBuilder;
    private final int rows;
    private final int columns;

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

    public String printField() {
        return renderTable(realm, columns);
    }

    private String renderTable(Cell[][] realm, int columns) {
        if (columns > 0 && columns <= 20) {
            AsciiTable field = new AsciiTable();
            for (int i = 0; i < rows; i++) {
                LinkedList<String> creaturesToPrint = new LinkedList<>();

                for (int j = 0; j < columns; j++) {
                    Cell cell = realm[i][j];
                    Map.Entry<Type, Set<Animal>> animalEntry = mostPopularAnimal(cell);
                    StringBuilder stringBuilder = new StringBuilder();

                    if (animalEntry == null) {
                        stringBuilder.append("-");
                    } else {
                        Type animal = animalEntry.getKey();
                        int quantity = animalEntry.getValue().size();
                        Map<Type, Object> prototypes = animalBuilder.prototypes;
                        Animal prototype = (Animal) prototypes.get(animal);
                        if (columns > 10) {
                            stringBuilder.append(prototype.getIcon());
                        } else {
                            stringBuilder.append(prototype.getIcon())
                                    .append(quantity);
                        }
                    }

                    stringBuilder.append("\\");

                    if (cell.getPlants().getMass() == 0) {
                        stringBuilder.append("-");
                    } else {
                        if (columns > 10) {
                            stringBuilder.append(cell.getPlants().getIcon());
                        } else {
                            stringBuilder.append(cell.getPlants().getIcon())
                                    .append((Long) Math.round(cell.getPlants().getMass()));
                        }
                    }
                    creaturesToPrint.add(stringBuilder.toString());
                }
                field.addRule();
                field.addRow(creaturesToPrint);
            }
            field.setTextAlignment(TextAlignment.LEFT);
            return field.render(10);
        }

        if (columns > 20) {
            return "Number of columns exceeds 20: game field render is not possible."; //TODO extract variable
        }
        return "Default string";
    }

    private Map.Entry<Type, Set<Animal>> mostPopularAnimal(Cell cell) {
        Optional<Map.Entry<Type, Set<Animal>>> animalOrNull = cell.getAnimals()
                .entrySet()
                .stream()
                .filter(o -> o.getValue().size() != 0)
                .min((o1, o2) -> Long.compare(o2.getValue().size(), o1.getValue().size()));

        return animalOrNull.orElse(null);
    }

    @Override
    public String toString() {
        return "GameField{" +
                "rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
