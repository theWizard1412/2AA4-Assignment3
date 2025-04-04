package ca.mcmaster.se2aa4.mazerunner;

public class LoggingObserver implements CommandObserver {

    @Override
    public void onCommandExecuted(Command command, Position position) {
        System.out.println("[Observer] Executed: " + command.getClass().getSimpleName() +
                           " | Position: (" + position.getX() + ", " + position.getY() + ") " +
                           "Facing: " + position.getDirection());
    }
}
