package entities;

import creatures.Creature;
import creatures.Plant;
import gamefield.GameField;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Stats {
    public static AtomicInteger numberOfIterations = new AtomicInteger(0);
    private GameField gameField;
    private Map<Type, Long> numberOfCreatures;

    public Stats(GameField gameField, Map<Type, Long> numberOfCreatures) {
        this.gameField = gameField;
        this.numberOfCreatures = numberOfCreatures;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Week #")
                .append(numberOfIterations)
                .append(", ")
                .append("population {");
        Map<Type, Object> prototypes = gameField.getAnimalBuilder().getPrototypes();

        for (var type: numberOfCreatures.entrySet()) {
            Creature creature = getCreaturePrototype(prototypes, type);

            stringBuilder.append(" ")
                    .append(creature.getIcon())
                    .append("-")
                    .append(type.getValue())
                    .append(";");
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private static Creature getCreaturePrototype(Map<Type, Object> prototypes, Map.Entry<Type, Long> type) {
        Object o = prototypes.get(type.getKey());

        if (o == null)
            return new Plant(0);

        return (Creature) o;
    }
}
