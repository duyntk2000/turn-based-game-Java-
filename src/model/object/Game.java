package model.object;

import model.object.board.BoardPrivate;
import model.object.character.GroupCharacterImpl;
import model.object.character.Character;
import model.player.PlayerAbstract;
import utils.AbstractModeleEcoutable;

/**
 * Represent the main game.
 */
public class Game extends AbstractModeleEcoutable {
    /**
     * group of characters/players.
     */
    private GroupCharacterImpl groupe;
    /**
     * current player.
     */
    private int currentPlayer;

    /**
     * Create a new game with specified Board and group of character.
     * @param pl the Board.
     * @param groupe the group of character.
     */
    public Game(BoardPrivate pl, GroupCharacterImpl groupe) {
        this.ajoutEcouteur(pl);
        this.groupe = groupe;
        for (PlayerAbstract player : groupe.getPlayers()) {
            this.ajoutEcouteur(player);
        }
    }

    /**
     * Start the game. Set the turn to first player.
     */
    public void gameStart() {
        this.currentPlayer = 0;
        fireChangement();
    }

    /**
     * Change the turn. Set the turn to next player.
     */
    public void nextPlayer() {
        this.currentPlayer = (this.currentPlayer + 1) % this.groupe.getPlayers().size();
        fireChangement();
    }

    /**
     * Get current player id.
     * @return the id.
     */
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Check if game's over. Either the number of players is less than 1 or every player energy is 0.
     * @return
     */
    public boolean gameOver() {
        boolean playerHasEnergy = false;
        for (Character p : this.groupe.getGroupe()) {
            if (p.getEnergy() > 0) {
                playerHasEnergy = true;
                break;
            }
        }
        return this.groupe.getTotalCharacters() <= 1 || !playerHasEnergy;
    }
}
