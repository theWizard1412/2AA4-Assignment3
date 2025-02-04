package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Position position;
    private Position end;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = new Position(maze.getStart());
        this.end = new Position(maze.getEnd());
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
                    position.move();
                    path.append('F');
                } else {
                    position.turn('L');
                    path.append('L');
                }
            } else {
                position.turn('R');
                path.append('R');
    
                if (canMoveForward(position)) {
                    position.move();
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
                return maze.isWall(x + 1, y);  // Can move right (east)
            case 'E':
                return maze.isWall(x, y + 1);  // Can move right (south)
            case 'S':
                return maze.isWall(x - 1, y);  // Can move right (west)
            case 'W':
                return maze.isWall(x, y - 1);  // Can move right (north)
            default:
                return false;
        }
    }
    
    
    
    private boolean canMoveRight() {
        // Determine if we can turn right
        int x = position.getX();
        int y = position.getY();
        switch (position.getDirection()) {
            case 'N':
                return maze.isPassage(x + 1, y);  // Can move right (east)
            case 'E':
                return maze.isPassage(x, y + 1);  // Can move right (south)
            case 'S':
                return maze.isPassage(x - 1, y);  // Can move right (west)
            case 'W':
                return maze.isPassage(x, y - 1);  // Can move right (north)
            default:
                return false;
        }
    }
    
    private boolean canMoveLeft() {
        // Determine if we can turn left
        int x = position.getX();
        int y = position.getY();
        switch (position.getDirection()) {
            case 'N':
                return maze.isPassage(x - 1, y);  // Can move left (west)
            case 'E':
                return maze.isPassage(x, y - 1);  // Can move left (north)
            case 'S':
                return maze.isPassage(x + 1, y);  // Can move left (east)
            case 'W':
                return maze.isPassage(x, y + 1);  // Can move left (south)
            default:
                return false;
        }
    }
    
    
    private boolean canMoveForward(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        switch (pos.getDirection()) {
            case 'N':
                return maze.isPassage(x, y - 1);  // Can move north
            case 'E':
                return maze.isPassage(x + 1, y);  // Can move east
            case 'S':
                return maze.isPassage(x, y + 1);  // Can move south
            case 'W':
                return maze.isPassage(x - 1, y);  // Can move west
            default:
                return false;
        }
    }    
    

    
}
