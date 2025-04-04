package ca.mcmaster.se2aa4.mazerunner;

public interface Command { 
  void execute(); // This method returns the Position that is affected by the command 
  Position getTargetPosition();
}
