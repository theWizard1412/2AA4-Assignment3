package ca.mcmaster.se2aa4.mazerunner;

public class Position {
  
  private int x;
  private int y;
  private char direction;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
    direction = 'N';
  }

  public Position() { //fix later
    this.x = 0;
    this.y = 0;
  }

  public Position(int y) {
    this.x = 0;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void move() {
    if (direction == 'N') { //note if we go north we are subtracting because y is 0 on top
      y--;
    }
    else if (direction == 'E') {
      x++;
    }
    else if (direction == 'S') {
      y++;
    }
    else {
      x--;
    }
  }

  public void turn(char turning) {
    if (direction == 'N') {
      if (turning == 'L') {
        direction = 'W';
      }
      else {
        direction = 'E';
      }
    }
    else if (direction == 'E') {
      if (turning == 'L') {
        direction = 'N';
      }
      else {
        direction = 'S';
      }
    }

    else if (direction == 'S') {
      if (turning == 'L') {
        direction = 'E';
      }
      else {
        direction = 'W';
      }
    }
    else {
      if (turning == 'L') {
        direction = 'S';
      }
      else {
        direction = 'N';
      }
    }
  }

  public boolean equals(Position position) {
    if (this == position) {
      return true;
    }

    if ((position.getX() == x) && (position.getY() == y)) {
      return true;
    }

    else {
      return false;
    }
  }
}
