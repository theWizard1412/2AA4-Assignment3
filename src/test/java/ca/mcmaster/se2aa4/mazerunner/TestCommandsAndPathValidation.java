package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCommandsAndPathValidation {

    @Test
    public void testMoveCommandFacingEast() {
        Position pos = new Position(3); // starts at (0, 3), facing East
        Command move = new MoveCommand(pos);
        move.execute();
        assertEquals(1, pos.getX());
        assertEquals(3, pos.getY());
    }

    @Test
    public void testTurnCommandLeftFromEast() {
        Position pos = new Position(3); // facing East
        Command turn = new TurnCommand(pos, 'L');
        turn.execute();
        assertEquals('N', pos.getDirection());
    }

    @Test
    public void testTurnCommandRightFromEast() {
        Position pos = new Position(3); // facing East
        Command turn = new TurnCommand(pos, 'R');
        turn.execute();
        assertEquals('S', pos.getDirection());
    }

    @Test
    public void testInvalidPathHitsWall() {
        Maze maze = new Maze();
        maze.loadFromFile("./examples/small.maz.txt");
        Position pos = new Position(maze.getStart()); // uses (0, y), facing East
        Path path = new Path(maze, pos);

        path.addMovement("F F F F F F F F F"); // Assumes crash into wall
        boolean result = path.validatePath();

        assertEquals(false, result);
    }

}
