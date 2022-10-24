package entities;

import game.Settings;
import utils.Dice;

public enum Terrain {
    BASIC_TERRAIN,
    RIVER,
    MOUNTAINS;

    public static Terrain generateTerritory() {
        double dice = Dice.random(0, 1.0);
        if (dice <= Settings.BASIC_TERRAIN_CHANCE) return BASIC_TERRAIN;
        if (dice > Settings.BASIC_TERRAIN_CHANCE && dice <= Settings.MOUNTAINS_CHANCE) return RIVER;
        if (dice > Settings.MOUNTAINS_CHANCE) return MOUNTAINS;
        return BASIC_TERRAIN;
    }
}
