package model.object.utility;

import utils.AbstractModeleEcoutable;

import java.util.ArrayList;

/**
 * Record all the Action.
 */
public class History extends AbstractModeleEcoutable {
    /**
     * list of actions.
     */
    private ArrayList<Action> history;
    /**
     * last action's description.
     */
    private String lastMove;


    public History() {
        this.history = new ArrayList<>();
    }

    public ArrayList<Action> getHistory() {
        return history;
    }

    /**
     * Get last action's description.
     * @return last action's description.
     */
    public String getLastMove() {
        return lastMove;
    }

    /**
     * Add action to history.
     * @param a action.
     */
    public void addAction(Action a) {
        this.history.add(a);
        this.lastMove = a.toString();
        fireChangement();
    }
}
