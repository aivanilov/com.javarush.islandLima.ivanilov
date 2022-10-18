package creatures.herbivores;

import creatures.Animal;
import creatures.Creature;
import creatures.Plant;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class Herbivore extends Animal {

    {
        Map<Type, Integer> foodPreferences = new HashMap<>();
        foodPreferences.put(Plant.class, 100);
        setFoodPreferences(foodPreferences);
    }

}
