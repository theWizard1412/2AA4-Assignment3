package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*; // Library for options and -i flag
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input", true, "Maze file");
        options.addOption("p", "path", true, "Path String");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            // check if no -i flag
            if (!cmd.hasOption("i")) {
                logger.error("No input file specified. Use -i <filename>.");
                System.out.println("Usage: java -jar maze-runner.jar -i <maze-file>");
                return;
            }

            String inputFile = cmd.getOptionValue("i");
            logger.info("*** Reading the maze from file: {}", inputFile);

            // load maze
            Maze maze = new Maze();
            maze.loadFromFile(inputFile);
            

            logger.info("*** Maze loaded successfully:");
            
            /* 
            for (int y = 0; y < maze.getHeight(); y++) {
                for (int x = 0; x < maze.getWidth(); x++) {
                    if (maze.getCell(x, y) == '#') {
                        System.out.print("WALL" + " ");
                    }
                    else {
                        System.out.print("PASS" + " ");
                    }
                }
                System.out.println();
            }
            */

            if (cmd.hasOption("p")) {
                String pathString = cmd.getOptionValue("p");

                Position start_position = new Position(maze.getStart());
                Position end_position = new Position(maze.getWidth()-1, maze.getEnd());

                Path path_forward = new Path(maze, start_position);
                path_forward.addMovement(pathString);

                Path path_backward = new Path(maze, end_position);
                path_backward.addMovement(pathString);

                boolean check_forward = path_forward.validatePath();
                boolean check_bacbward = path_backward.validatePath();

                if (check_forward || check_bacbward) {
                    System.out.println("correct path");
                }
                else {
                    System.out.println("incorrect path");
                }
            }

            else {
            
                Explorer explorer = new Explorer(maze);
                explorer.explore();
            }

        } catch (ParseException e) {
            logger.error("Failed to parse command-line arguments.", e);
        } catch (Exception e) {
            logger.error("An error occurred while processing the maze file.", e);
        }
    }
}
