package utils;

import exceptions.IslandGameException;

public class Threader {
    private Runnable runnable;

    public Threader(Runnable runnable) {
        this.runnable = runnable;
    }

    public void execute() {
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new IslandGameException(e);
        }
    }
}
