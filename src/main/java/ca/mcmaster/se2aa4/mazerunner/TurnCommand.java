package ca.mcmaster.se2aa4.mazerunner;

public class TurnCommand implements Command {
  private ObservablePosition position;
  private char direction;

  public TurnCommand(ObservablePosition position, char direction) {
      this.position = position;
      this.direction = direction;
  }

  @Override
  public void execute() {
      position.turn(direction);
  }
}
