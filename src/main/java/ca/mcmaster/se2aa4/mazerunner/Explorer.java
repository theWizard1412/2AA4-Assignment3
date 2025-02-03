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

       /*  while ((x_position >=0 ) && (x_position < maze.getWidth()) && (y_position >= 0) && (y_position < maze.getHeight())) {

            if (x_position + 1 >= maze.getWidth()) {
                break;
            }

            x_position++;
            F_count++;

            if(maze.isWall(x_position, y_position)) {
                logger.info("Path not find");
                return;
            } 
        }
            */

            /* 
        for (int i = 0; i < F_count; i++) {
            System.out.print("F");
        }
        System.out.println();

        */

        rightHandAlgorithm();
        
        logger.info("End of MazeRunner");
    }
    
    public void rightHandAlgorithm() {
        while (position.getX() < maze.getWidth()-1) {
    
            if (rightIsWall()) { //if right is wall, meaning right hand is currently on the wall
                if (canMoveForward(position)) {
                    position.move();
                    System.out.print('F');
                }
                else {
                    position.turn('L'); //turn left because right is wall
                    System.out.print('L');
                }
            }

            else {
                position.turn('R');
                System.out.print('R');

                if (canMoveForward(position)) {
                    position.move();
                    System.out.print('F');
                }
                
            }
        }
    
        System.out.println(); // Move to a new line after printing the path
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
