package model.object.board;

import model.object.BoardObject;

/**
 * Wall-setting strategy for board of object.
 */
public interface WallStrategy {
    /**
     * Method to add walls.
     * @param plateau the board of object.
     * @param size the board size.
     */
    void addWall(BoardObject[] plateau, int size);
}
