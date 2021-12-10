package controller;

import model.object.Game;

/**
 * Controller that in charge of starting game and changing turn.
 */
public class ControllerGame {
    /**
     * the Game
     */
    protected Game game;

    /**
     * Create a new ControllerGame with the specified Game.
     * @param game the Game to use.
     * @see Game
     */
    public ControllerGame(Game game) {
        this.game = game;
    }

    /**
     * Starting game, set the turn to the first player.
     */
    public void startGame() {
        this.game.gameStart();
    }

    /**
     * Changing turn, set the turn to the next player.
     */
    public void nextPlayer() {
        this.game.nextPlayer();
    }
}
