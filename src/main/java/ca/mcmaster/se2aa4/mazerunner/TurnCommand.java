package ca.mcmaster.se2aa4.mazerunner;

public class TurnCommand implements Command {
  private Position position;
  private char direction;

  public TurnCommand(Position position, char direction) {
      this.position = position;
      this.direction = direction;
  }

  @Override
  public void execute() {
      position.turn(direction);
  }
}
