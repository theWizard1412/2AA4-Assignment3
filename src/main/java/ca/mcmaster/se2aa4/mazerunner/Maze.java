package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private char[][] layout; 
    private int width;
    private int height;

    public Maze() {
        
    }

    public void loadFromFile(String fileName) {
        
    }

    public char getCell(int x, int y) {
        
        return '\0'; 
    }

    public int getWidth() {
        //getter methods, maybe not required?
        return 0;
    }

    public int getHeight() {
        
        return 0;
    }

    public boolean isWall(int x, int y) {
        
        return false;
    }

    public boolean isPassage(int x, int y) {
        
        return false;
    }

    public int getStart() {
        //add start postion
        return -1;
    }

    public int getEnd() {
        //add end postion
        return -1;
    }
}
