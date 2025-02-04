package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private String inputPath;
    private Maze maze;
    private Position position;

    public Path(Maze maze, Position position) {
        this.maze = maze;
        this.position = position;
    }

    public void addMovement(String inputPath) {
        this.inputPath = inputPath;
    }

    public boolean validatePath() {
        // check if path is valide
        //note that maze is non-directional, beginign and end point may exchange
        char[] movement = inputPath.toCharArray();

        for (int i = 0; i < movement.length; i++) {
            if (movement[i] == ' ') {
                continue; // Skip spaces
            }
            if (Character.isDigit(movement[i])) {
                int num = movement[i] - '0'; //cast char to int
                i++;
                for (int j = 0; j < num; j++) {
                    moveStep(movement[i]);
                    if (maze.isWall(position.getX(), position.getY())){
                        return false;
                    }
                }
            }
            else {
                moveStep(movement[i]);
                if (maze.isWall(position.getX(), position.getY())){
                    return false;
                }
            }
        }
        
        if ((position.getX() == maze.getWidth()-1) || (position.getX() == 0)){
            return true;
        }

        return false;
    }

    
    private void moveStep(char step) {
        if (step == 'F') {
            position.move();
        }
        else if (step == ' ') {
            //skip
        }
        else{
            position.turn(step);
        }
    }
}
