package model.player;

import controller.ControllerBoard;
import model.object.Game;
import model.object.character.Character;
import model.object.board.BoardPrivate;
import model.object.utility.Action;
import utils.EcouteurModele;

import java.util.ArrayList;

/**
 * Represent a player. Either a human or an AI.
 */
public abstract class PlayerAbstract implements Player, EcouteurModele {
    /**
     * the controller player uses.
     */
    protected ControllerBoard ctl;
    /**
     * player perspective.
     */
    protected BoardPrivate pl;
    /**
     * the character player controls.
     */
    protected Character p;
    /**
     * player id.
     */
    protected int playerID;

    public PlayerAbstract(ControllerBoard ctl, BoardPrivate pl, Character p) {
        this.ctl = ctl;
        this.pl = pl;
        this.p = p;
        this.playerID = p.getPlayerID();
    }

    @Override
    public void modeleMisAJour(Object source) {
        Game game = (Game) source;
        if (game.getCurrentPlayer() == this.p.getPlayerID()) {
            try {
                this.chooseAction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the character that player is controlling.
     * @return the character.
     */
    public Character getCharacter() {
        return this.p;
    }

    /**
     * Get every possible moves.
     * @return list of moves.
     */
    public ArrayList<Action> getMoves() {
        ArrayList<Integer> actions = p.getActionPossible();
        ArrayList<Action> list = new ArrayList<>();
        ArrayList<Integer[]> position = pl.getDirection(4, p.getPos());

        if (actions.contains(0)) {
            ArrayList<Integer[]> pos = pl.filter(position, false);
            pos.forEach(p -> list.add(new Action(0, p[0], p[1], this.playerID)));
        }
        if (actions.contains(3)) {
            ArrayList<Integer[]> pos = pl.filter(position, true);
            pos.forEach(p -> list.add(new Action(3, p[0], p[1], this.playerID)));
        }

        position = pl.getDirection(8, p.getPos());
        position = pl.filter(position, false);
        if (actions.contains(1)) {
            position.forEach(pos -> list.add(new Action(1, pos[0], pos[1], this.playerID)));
        }
        if (actions.contains(2)) {
            position.forEach(pos -> list.add(new Action(2, pos[0], pos[1], this.playerID)));
        }

        if (actions.contains(4)) {
            list.add(new Action(4, 0, 0, this.playerID));
        }

        if (actions.contains(5)) {
            list.add(new Action(5, 0, 0, this.playerID));
        }

        return list;
    }
}
