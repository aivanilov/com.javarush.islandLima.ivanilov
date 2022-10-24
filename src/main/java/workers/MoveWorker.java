package workers;

import behaviours.Nesting;
import entities.Direction;
import game.Cell;
import utils.Dice;
import utils.Waiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoveWorker implements Runnable {
    private final Cell cell;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MoveWorker(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        cell.getAnimals()
                .forEach((type, animals) -> animals.forEach(animal -> {
                    int speed = animal.getAnimalLimits().getSpeed();

                    if(animal.getClass().isAnnotationPresent(Nesting.class)) {
                        if (animal.isPregnant()) {
                            speed = 0;
                        }
                    }

                    if (speed != 0) {

                        Cell destination = null;
                        while (speed > 0) {
                            Direction randomDirection = Dice.getRandomSetElement(this.cell.getSurroundings().keySet());
                            boolean canMove = animal.move(this.cell.getSurroundings().get(randomDirection));
                            if (canMove) {
                                destination = this.cell.getSurroundings().get(randomDirection);
                                if (destination != this.cell) {
                                    speed--;
                                }
                            }
                        }

                        assert destination != null;
                        TransferWorker transferWorker = new TransferWorker(cell, destination, animal);
                        executorService.submit(transferWorker);
                    }
                }));
        Waiter.awaitTermination(executorService);
    }
}
