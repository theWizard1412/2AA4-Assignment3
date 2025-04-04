package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
 import java.util.List;
public class CommandInvoker {   private List<Command> commandHistory = new ArrayList<>();

  public void execute(Command command) {
    command.execute();
    commandHistory.add(command);
}

public List<Command> getCommandHistory() {
    return commandHistory;
}

}