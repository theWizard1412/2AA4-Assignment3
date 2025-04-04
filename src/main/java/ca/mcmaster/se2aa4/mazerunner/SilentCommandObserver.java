package ca.mcmaster.se2aa4.mazerunner;

public class SilentCommandObserver implements CommandObserver { 
  @Override public void onCommandExecuted(Command command, Position position) { // This observer intentionally does nothing. 
  } 
}