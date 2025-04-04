package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {   
  private List<Command> commandHistory = new ArrayList<>();

  private List<CommandObserver> observers = new ArrayList<>();

  public void addObserver(CommandObserver observer) {
   observers.add(observer);
  }

  public void execute(Command command) {
    command.execute();
    commandHistory.add(command);
    notifyObservers(command);
  }

  private void notifyObservers(Command command) {
      Position pos = command.getTargetPosition();
      for (CommandObserver observer : observers) {
          observer.onCommandExecuted(command, pos);
      }
  }

  public List<Command> getCommandHistory() {
      return commandHistory;
  }


}