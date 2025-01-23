package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

            if(!cmd.hasOption("i")) {
                logger.error("No input file specified. Use -i <filename>.");
                System.out.println("Usage: java -jar maze-runner.jar -i <maze-file>");
                return;
            }

            String inputFile = cmd.getOptionValue("i");

            logger.info("Reading the maze from file: {}", inputFile);
            
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            System.out.print("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            System.out.print("PASS ");
                        }
                    }
                    System.out.print(System.lineSeparator());
                }
            }

        } catch (ParseException e) {
            logger.error("Failed to parse command-line arguments.", e);
        } catch (Exception e) {
            logger.error("An error occurred while processing the maze file.", e);
        }
    }
}
