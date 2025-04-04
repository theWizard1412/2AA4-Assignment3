package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class ObservablePosition extends Position {
    private List<CommandObserver> observers = new ArrayList<>();

    public ObservablePosition(int y) {
        super(y);
    }

    public ObservablePosition(int x, int y) {
        super(x, y);
    }

    public void addObserver(CommandObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Command command) {
        for (CommandObserver observer : observers) {
            observer.onCommandExecuted(command, this);
        }
    }

    // Override move and turn to include notification
    @Override
    public void move() {
        super.move();
        notifyObservers(new MoveCommand(this));
    }

    @Override
    public void turn(char dir) {
        super.turn(dir);
        notifyObservers(new TurnCommand(this, dir));
    }
}
