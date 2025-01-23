package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;

    public Explorer(Maze maze) {
        this.maze = maze;
    }

    public void explore() {
        logger.info("Exploration started");
        //add explore algor
        logger.info("Exploration completed");
    }
}
