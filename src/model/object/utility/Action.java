package model.object.utility;

/**
 * Action represents an action or event.
 */
public class Action {
    /**
     * type of action.
     */
    private final int action;
    /**
     * x-coordinate of location.
     */
    private final int posX;
    /**
     * y-coordinate of location.
     */
    private final int posY;
    /**
     * player who cause action.
     */
    private final int playerID;

    /**
     * action type 0 : move.
     */
    public final static int MOVE_REF = 0;
    /**
     * action type 1 : set bombs.
     */
    public final static int BOMB_REF = 1;
    /**
     * action type 2 : set mine.
     */
    public final static int MINE_REF = 2;
    /**
     * action type 3 : shot.
     */
    public final static int SHOT_REF = 3;
    /**
     * action type 4 : use armor.
     */
    public final static int ARMOR_REF = 4;
    /**
     * action type 5 : rest.
     */
    public final static int REST_REF = 5;

    /**
     * text represents the action.
     */
    public final static String[] ACTION_REF = {"Move","Set Bomb","Set Mine","Shot","Use Armor","Rest","Bomb Explodes"};

    /**
     * Create new action.
     * @param action the type of action.
     * @param posX the x-coordinate of location.
     * @param posY the y-coordinate of location.
     * @param playerID the player causes the action/event.
     */
    public Action(int action, int posX, int posY, int playerID) {
        this.action = action;
        this.posX = posX;
        this.posY = posY;
        this.playerID = playerID;
    }

    /**
     * Get action type.
     * @return the type.
     */
    public int getAction() {
        return action;
    }

    /**
     * Get location x-coordinate.
     * @return x-coordinate.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Get location y-coordinate.
     * @return y-coordinate.
     */
    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        if (this.action != 6)
            return "Player " + this.playerID + " plays " + ACTION_REF[this.action] + " : (" + this.posX + "," + this.posY + ")" ;
        return "Player " + this.playerID + ACTION_REF[this.action] + " : (" + this.posX + "," + this.posY + ")" ;
    }
}
