package ca.mcmaster.se2aa4.mazerunner;

public interface CommandObserver {
  
  void onCommandExecuted(Command command, Position position); 
  
}
