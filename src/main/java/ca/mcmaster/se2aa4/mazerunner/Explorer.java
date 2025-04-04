package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private ObservablePosition position;
    private Position end;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = new ObservablePosition(maze.getStart());
        this.end = new Position(maze.getEnd());

        // Add a sample observer
        position.addObserver(new LoggingObserver());
    }

    public void explore() {
        logger.info("Computing Path");

        if (position.getY() == -1) {
            System.out.println("Start Position not found");
            return;
        }

        rightHandAlgorithm();

        logger.info("End of MazeRunner");
    }

    public void rightHandAlgorithm() {
        StringBuilder path = new StringBuilder();
        List<Command> commands = new ArrayList<>();

        while (position.getX() < maze.getWidth() - 1) {
            if (rightIsWall()) {
                if (canMoveForward(position)) {
                    Command moveCommand = new MoveCommand(position);
                    commands.add(moveCommand);
                    path.append('F');
                } else {
                    Command turnLeftCommand = new TurnCommand(position, 'L');
                    commands.add(turnLeftCommand);
                    path.append('L');
                }
            } else {
                Command turnRightCommand = new TurnCommand(position, 'R');
                commands.add(turnRightCommand);
                path.append('R');

                if (canMoveForward(position)) {
                    Command moveCommand = new MoveCommand(position);
                    commands.add(moveCommand);
                    path.append('F');
                }
            }
        }

        // Execute all commands
        for (Command command : commands) {
            command.execute();
        }

        System.out.println(factorizePath(path.toString()));
    }

    private String factorizePath(String path) {
        StringBuilder factorized = new StringBuilder();
        int count = 1;

        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == path.charAt(i - 1)) {
                count++;
            } else {
                if (count > 1) {
                    factorized.append(count);
                }
                factorized.append(path.charAt(i - 1)).append(" ");
                count = 1;
            }
        }

        if (count > 1) {
            factorized.append(count);
        }
        factorized.append(path.charAt(path.length() - 1));

        return factorized.toString().trim();
    }

    private boolean rightIsWall() {
        int x = position.getX();
        int y = position.getY();

        switch (position.getDirection()) {
            case 'N': return maze.isWall(x + 1, y);
            case 'E': return maze.isWall(x, y + 1);
            case 'S': return maze.isWall(x - 1, y);
            case 'W': return maze.isWall(x, y - 1);
            default: return false;
        }
    }

    private boolean canMoveForward(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        switch (pos.getDirection()) {
            case 'N': return maze.isPassage(x, y - 1);
            case 'E': return maze.isPassage(x + 1, y);
            case 'S': return maze.isPassage(x, y + 1);
            case 'W': return maze.isPassage(x - 1, y);
            default: return false;
        }
    }
}
