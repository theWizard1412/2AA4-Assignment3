package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> movements = new ArrayList<>();

    public void addMovement(String move) {
        movements.add(move);
    }

    public List<String> getMovements() {
        return movements;
    }

    public boolean validatePath() {
        // check if path is valide
        //note that maze is non-directional, beginign and end point may exchange
        return false;
    }
}
