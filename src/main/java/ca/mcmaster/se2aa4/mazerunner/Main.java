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

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            // Check if the -i flag is provided
            if (!cmd.hasOption("i")) {
                logger.error("No input file specified. Use -i <filename>.");
                System.out.println("Usage: java -jar maze-runner.jar -i <maze-file>");
                return;
            }

            String inputFile = cmd.getOptionValue("i");
            logger.info("Reading the maze from file: {}", inputFile);

            // Load the maze using the Maze class
            Maze maze = new Maze();
            maze.loadFromFile(inputFile);

            
            logger.info("Maze loaded successfully:");
            for (int y = 0; y < maze.getHeight(); y++) {
                for (int x = 0; x < maze.getWidth(); x++) {
                    System.out.print(maze.getCell(x-1, y-1));
                }
                System.out.println();
            }

            
            Explorer explorer = new Explorer(maze);
            explorer.explore();

        } catch (ParseException e) {
            logger.error("Failed to parse command-line arguments.", e);
        } catch (Exception e) {
            logger.error("An error occurred while processing the maze file.", e);
        }
    }
}
