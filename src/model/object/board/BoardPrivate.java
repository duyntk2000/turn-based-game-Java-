package model.object.board;

import model.object.BoardObject;
import model.object.Game;
import model.object.weapon.Mine;
import utils.AbstractModeleEcoutable;
import utils.EcouteurModele;

import java.util.ArrayList;

/**
 * The Board perspective of each character.
 */
public class BoardPrivate extends AbstractModeleEcoutable implements EcouteurModele {
    /**
     * the Board.
     */
    private Board plateau;
    /**
     * the player id, indicate which view it is.
     */
    private int playerID;

    /**
     * Create a new BoardPrivate with specified Board.
     * @param plateau the Board to use.
     */
    public BoardPrivate(Board plateau) {
        this.plateau = plateau;
        plateau.ajoutEcouteur(this);
    }

    /**
     * Get the map of all the explosions of just the current player.
     * @return the array of Mine.
     */
    public Mine[] getMapExplosions() {
        Mine[] map = this.plateau.getMapExplosions();
        for (Mine mine : map) {
            if (mine != null)
                mine.visible = mine.getOwnerID() == this.playerID;
        }
        return map;
    }

    public int getSize() { return this.plateau.getSize(); }

    public BoardObject[] getPlateau() { return this.plateau.getPlateau(); }

    public int getCurrentPlayer() { return this.playerID; }

    public Board getBoard() { return this.plateau; }

    @Override
    public void modeleMisAJour(Object source) {
        if (source instanceof Game) {
            this.playerID = ((Game) source).getCurrentPlayer();
        }
        fireChangement();
    }

    public ArrayList<Integer[]> getDirection(int n, int pos) {
        return this.plateau.getDirection(n,pos);
    }

    public ArrayList<Integer[]> filter(ArrayList<Integer[]> moves, boolean shot) {
        return this.plateau.filter(moves, shot);
    }
}
