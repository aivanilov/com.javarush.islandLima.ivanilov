package utils;

import exceptions.IslandGameException;

public class Sleeper {

    public static void sleep() {
        try{
            Thread.sleep(100);
        } catch (Exception e) {
            throw new IslandGameException("Thread has been interrupted");
        }
    }

    public static void sleep(long milliseconds) {
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            throw new IslandGameException("Thread has been interrupted");
        }
    }
}
