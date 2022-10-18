package actions;

public interface Nesting {

    default void move() {
        //TODO should not move when "pregnant" (sitting on eggs)
    }
}
