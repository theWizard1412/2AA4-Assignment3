package ca.mcmaster.se2aa4.mazerunner;

public class MoveCommand implements Command {
  private Position position;

  public MoveCommand(Position position) {
      this.position = position;
  }

  @Override
  public void execute() {
      position.move();
  }
}