package model.object;

import utils.AbstractModeleEcoutable;


/**
 * Represent a board object in general with its current position and ancient position.
 */
public abstract class BoardObject extends AbstractModeleEcoutable {
    /**
     * current position. If equals -1, the object/character is destroyed/dead .
     */
    protected int pos;
    /**
     * ancient position.
     */
    protected int oldPos;

    /**
     * Get current position.
     * @return current position.
     */
    public int getPos() {
        return this.pos;
    }

    /**
     * Set current position.
     * @param pos current position.
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Get ancient position.
     * @return ancient position.
     */
    public int getOldPos() {
        return this.oldPos;
    }

    /**
     * Set ancient position.
     * @param pos ancient position.
     */
    public void setOldPos(int pos) {
        this.oldPos = pos;
    }
}
