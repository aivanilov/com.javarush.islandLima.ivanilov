package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BreedingParameters {
    boolean isFemale;
    boolean isPregnant;
    private int pregnancyCounter;
    private int pregnancyLength;
    private int minOffsprings;
    private int maxOffsprings;

    public BreedingParameters() {
    }

    @Override
    public String toString() {
        return "BreedingParameters{" +
                "isFemale=" + isFemale +
                ", isPregnant=" + isPregnant +
                ", pregnancyCounter=" + pregnancyCounter +
                ", pregnancyLength=" + pregnancyLength +
                ", minOffsprings=" + minOffsprings +
                ", maxOffsprings=" + maxOffsprings +
                '}';
    }
}