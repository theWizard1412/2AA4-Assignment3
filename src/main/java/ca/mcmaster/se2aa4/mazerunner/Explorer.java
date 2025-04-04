package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Position position;
    private Position end;
    private CommandInvoker invoker;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = new Position(maze.getStart());
        this.end = new Position(maze.getEnd());
        this.invoker = new CommandInvoker();
        
        invoker.addObserver(new SilentCommandObserver());
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
    
        while (position.getX() < maze.getWidth() - 1) {
            if (rightIsWall()) {
                if (canMoveForward(position)) {
                    invoker.execute(new MoveCommand(position));
                    path.append('F');
                } else {
                    invoker.execute(new TurnCommand(position, 'L'));
                    path.append('L');
                }
            } else {
                invoker.execute(new TurnCommand(position, 'R'));
                path.append('R');
    
                if (canMoveForward(position)) {
                    invoker.execute(new MoveCommand(position));
                    path.append('F');
                }
            }
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
            case 'N':
                return maze.isWall(x + 1, y);  // Right of North is East
            case 'E':
                return maze.isWall(x, y + 1);  // Right of East is South
            case 'S':
                return maze.isWall(x - 1, y);  // Right of South is West
            case 'W':
                return maze.isWall(x, y - 1);  // Right of West is North
            default:
                return false;
        }
    }
    
    private boolean canMoveForward(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        switch (pos.getDirection()) {
            case 'N':
                return maze.isPassage(x, y - 1);
            case 'E':
                return maze.isPassage(x + 1, y);
            case 'S':
                return maze.isPassage(x, y + 1);
            case 'W':
                return maze.isPassage(x - 1, y);
            default:
                return false;
        }
    }
}    