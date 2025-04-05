package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestMazeRunner {
  private Explorer explorer;
  private Maze maze = getSmallMaze();
  private Path path;
  private Position position;

  private Maze getSmallMaze() {
    maze = new Maze();

    maze.loadFromFile("./examples/small.maz.txt");

    // return maze object
    return maze;
  }

  @Test
  public void testGetStart() {
        // use the maze object to check starting y location
    int start = this.maze.getStart();
    
        
    assertEquals(8, start); 
    
  }

  @Test
  public void testGetEnd() {
    //test get end y location
    int end = this.maze.getEnd();

    assertEquals(5, end);  

  }


  @Test
  public void testGetCell() {
    //test getCell(int x, int y) which returns the cell at that position
    char cell = this.maze.getCell(0, 0);

    assertEquals(cell, '#');
  }

  @Test
  public void testGetWidth() {
    // test if getWidth() returns proper width of the maze
    int width = this.maze.getWidth();

    assertEquals(width, 11);
  }

  @Test 
  public void testGetHeight() {
    // test if getHeight returns proper height of the maze
    int height = this.maze.getHeight();

    assertEquals(height, 11);
  }

  @Test
  public void testIsWall() {

    boolean wall = this.maze.isWall(0,0);

    assertEquals(wall, true);
  }

  @Test
  public void testIsPassage() {

    boolean passage = this.maze.isPassage(0, 0);
    assertEquals(passage, false);
  }

  @Test
  public void testGetX() {
    position = new Position(3, 4);

   int x = this.position.getX();
   assertEquals(x, 3);
  }

  @Test
  public void testGetY() {
    position = new Position(3, 5);

    int y = this.position.getY();
    assertEquals(y, 5);
  }
  
  
  @Test
  public void testValidatingPath() {

    position = new Position(this.maze.getStart());
    path = new Path(this.maze, position);

    path.addMovement("F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F");

    boolean validate = this.path.validatePath();

    assertEquals(true, validate);
  }
     


}