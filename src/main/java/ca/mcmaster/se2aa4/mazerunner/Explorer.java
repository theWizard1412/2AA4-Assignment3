package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Position position;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = new Position(maze.getStart());
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
        
        logger.info("End of MazeRunner");
    }
}
