package creatures;

import actions.Reproducible;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class Creature implements Reproducible {
    public static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private String icon;
}
