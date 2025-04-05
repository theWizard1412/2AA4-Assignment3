package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList; 
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandObserverPatternTest {
  private Position position;
private CommandInvoker invoker;
private TestObserver testObserver;

// A simple observer that records notifications.
private class TestObserver implements CommandObserver {
    private List<String> notifications = new ArrayList<>();

    @Override
    public void onCommandExecuted(Command command, Position pos) {
        notifications.add(command.getClass().getSimpleName() + " executed; Position: (" +
                          pos.getX() + ", " + pos.getY() + ") facing " + pos.getDirection());
    }

    public List<String> getNotifications() {
        return notifications;
    }
}

@BeforeEach
public void setUp() {
    // Using Position(int y) constructor creates a starting position at (0,3) facing 'E'
    position = new Position(3);
    invoker = new CommandInvoker();
    testObserver = new TestObserver();
    invoker.addObserver(testObserver);
}

@Test
public void testMoveCommand() {
    // Starting at (0,3) facing 'E': a move should increment x to 1.
    MoveCommand move = new MoveCommand(position);
    invoker.execute(move);
    assertEquals(1, position.getX(), "After move, x should be 1.");
    assertEquals(3, position.getY(), "After move, y should remain 3.");

    List<String> notifications = testObserver.getNotifications();
    assertEquals(1, notifications.size(), "Observer should have one notification.");
    assertTrue(notifications.get(0).contains("MoveCommand"),
               "Notification should mention MoveCommand.");
}

@Test
public void testTurnCommand() {
    // Starting at (0,3) facing 'E': a left turn should change direction to 'N'.
    TurnCommand turn = new TurnCommand(position, 'L');
    invoker.execute(turn);
    assertEquals('N', position.getDirection(), "After turn left, direction should be 'N'.");

    List<String> notifications = testObserver.getNotifications();
    assertEquals(1, notifications.size(), "Observer should have one notification.");
    assertTrue(notifications.get(0).contains("TurnCommand"),
               "Notification should mention TurnCommand.");
}

@Test
public void testMultipleCommands() {
    // Execute a sequence: Turn left (E -> N) then move (moving north decrements y).
    TurnCommand turnLeft = new TurnCommand(position, 'L');
    invoker.execute(turnLeft);
    // After turning left, the direction becomes 'N' (starting from (0,3)).
    MoveCommand move = new MoveCommand(position);
    invoker.execute(move);
    // Moving north should decrement y by 1 (from 3 to 2).
    assertEquals('N', position.getDirection(), "Direction should be 'N'.");
    assertEquals(0, position.getX(), "x should remain 0.");
    assertEquals(2, position.getY(), "y should decrement to 2.");

    // Verify that two notifications were received.
    List<String> notifications = testObserver.getNotifications();
    assertEquals(2, notifications.size(), "Observer should have two notifications.");
}

}