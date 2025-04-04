package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCommandsAndPathValidation {

    @Test
    public void testMoveCommandFacingEast() {
        ObservablePosition pos = new ObservablePosition(3); // starts at (0, 3), facing East
        pos.addObserver(new LoggingObserver()); // Optional: for output
        Command move = new MoveCommand(pos);
        move.execute();
        assertEquals(1, pos.getX());
        assertEquals(3, pos.getY());
    }

    @Test
    public void testTurnCommandLeftFromEast() {
        ObservablePosition pos = new ObservablePosition(3); // facing East
        pos.addObserver(new LoggingObserver());
        Command turn = new TurnCommand(pos, 'L');
        turn.execute();
        assertEquals('N', pos.getDirection());
    }

    @Test
    public void testTurnCommandRightFromEast() {
        ObservablePosition pos = new ObservablePosition(3); // facing East
        pos.addObserver(new LoggingObserver());
        Command turn = new TurnCommand(pos, 'R');
        turn.execute();
        assertEquals('S', pos.getDirection());
    }

    @Test
    public void testInvalidPathHitsWall() {
        Maze maze = new Maze();
        maze.loadFromFile("./examples/small.maz.txt");
        ObservablePosition pos = new ObservablePosition(maze.getStart());
        pos.addObserver(new LoggingObserver());
        Path path = new Path(maze, pos);

        path.addMovement("F F F F F F F F F"); // Should hit a wall eventually
        boolean result = path.validatePath();

        assertEquals(false, result);
    }
}
