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
    
            if (!cmd.hasOption("i")) {
                logger.error("No input file specified. Use -i <filename>.");
                System.out.println("Usage: java -jar maze-runner.jar -i <maze-file>");
                return;
            }
    
            String inputFile = cmd.getOptionValue("i");
            logger.info("*** Reading the maze from file: {}", inputFile);
    
            Maze maze = new Maze();
            maze.loadFromFile(inputFile);
    
            logger.info("*** Maze loaded successfully:");
    
            String pathString = ""; //handles spaces after -p falg
            if (cmd.hasOption("p")) {
                boolean readingPath = false;
                for (int i = 0; i < args.length; i++) {
                    if ("-p".equals(args[i])) {
                        readingPath = true;
                    } else if (readingPath) {
                        if (args[i].startsWith("-")) {
                            break; 
                        }
                        pathString += args[i] + " "; 
                    }
                }
            }
    
            if (!pathString.isEmpty()) {
                logger.info("*** Path string: {}", pathString.trim());
    
                Position start_position = new Position(maze.getStart());
                Position end_position = new Position(maze.getWidth() - 1, maze.getEnd());
    
                Path path_forward = new Path(maze, start_position);
                path_forward.addMovement(pathString.trim());
    
                Path path_backward = new Path(maze, end_position);
                path_backward.addMovement(pathString.trim());
    
                boolean check_forward = path_forward.validatePath();
                boolean check_backward = path_backward.validatePath();
    
                if (check_forward || check_backward) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
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
