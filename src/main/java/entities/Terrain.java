package entities;

import utils.Dice;

import java.util.concurrent.ThreadLocalRandom;

public enum Terrain {
    BASIC_TERRAIN,
    RIVER,
    MOUNTAINS;

    public static Terrain generateTerritory() {
        double dice = Dice.random(0, 1.0);
        if (dice <= 0.8) return BASIC_TERRAIN;
        if (dice > 0.8 && dice <= 0.95) return RIVER;
        if (dice > 0.95) return MOUNTAINS;
        return BASIC_TERRAIN;
    }
}
