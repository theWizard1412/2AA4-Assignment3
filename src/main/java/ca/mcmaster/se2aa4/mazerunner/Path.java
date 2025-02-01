package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private String inputPath;
    private Position position;
    private Maze maze;

    public Path(Maze maze) {
        this.maze = maze;
        this.position = new Position(maze.getStart());
    }

    public void addMovement(String inputPath) {
        this.inputPath = inputPath;
    }

    public boolean validatePath() {
        // check if path is valide
        //note that maze is non-directional, beginign and end point may exchange
        char[] movement = inputPath.toCharArray();

        for (int i = 0; i < movement.length; i++) {
            if (Character.isDigit(movement[i])) {
                int num = movement[i] - '0'; //cast char to int
                i++;
                for (int j = 0; j < num; j++) {
                    moveStep(movement[i]);
                }
            }
            else {
                moveStep(movement[i]);
            }
        }
        return false;
    }

    private boolean moveStep(char step) {
        if (step == 'F') {
            position.move();
        }
        else if (step == ' ') {
            //skip
        }
        else{
            position.turn(step);
        }
        return false;
    }
}
