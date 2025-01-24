package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    private char[][] layout; 
    private int width;
    private int height;

    public Maze() {
        
    }

    public void loadFromFile(String fileName) {

      List<String> lines = new ArrayList<>();

      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
          String line;
          while ((line = reader.readLine()) != null) {
              lines.add(line); // Add each line to the list
          }
      } catch (IOException e) {
          throw new RuntimeException("Error reading maze file: " + fileName, e);
      }

      this.height = lines.size();
      this.width = lines.isEmpty() ? 0 : lines.get(0).length();

      this.layout = new char[height][width];

      for (int i = 0; i < height; i++) {
          layout[i] = lines.get(i).toCharArray();
      }
        
    }

    public char getCell(int x, int y) {

      if ((x >= 0) && (x < width) && (y >= 0) && (y < height)) {
        return layout[y][x];
      }
        
        return '\0'; 
    }

    public int getWidth() {

      
      return width;
    }

    public int getHeight() {
        
        return height;
    }

    public boolean isWall(int x, int y) {

      return getCell(x, y) == '#';
    }

    public boolean isPassage(int x, int y) {
      
      return getCell(x, y) == ' ';
    }

    public int getStart() {
        for (int i = 0; i < height; i++) {
          if(layout[i][0] == ' ') {
            return i;
          }
        }
        return -1;
    }

    public int getEnd() {
        //add end postion
        for (int i = 0; i < height; i++) {
          if(layout[i][width-1] == ' ') {
            return i;
          }
        }
        return -1;
    }
}
