package ca.mcmaster.se2aa4.mazerunner;

public class TurnCommand implements Command {     
  private Position position; 
  private char turnDirection;

  public TurnCommand(Position position, char turnDirection) {
    this.position = position;
    this.turnDirection = turnDirection;
  }

  @Override
  public void execute() {
    position.turn(turnDirection);
  }

  @Override
  public Position getTargetPosition() {
    return position;
  }

}