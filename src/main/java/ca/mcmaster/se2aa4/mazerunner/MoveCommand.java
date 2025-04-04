package ca.mcmaster.se2aa4.mazerunner;

public class MoveCommand implements Command {
    private ObservablePosition position;

    public MoveCommand(ObservablePosition position) {
        this.position = position;
    }

    @Override
    public void execute() {
        position.move();
    }
}
