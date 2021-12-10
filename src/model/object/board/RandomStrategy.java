package model.object.board;

import model.object.BoardObject;
import model.object.utility.Wall;

/**
 * The Strategy to create walls randomly.
 */
public class RandomStrategy implements WallStrategy{
    @Override
    public void addWall(BoardObject[] plateau, int size) {
        for (int i = 0; i < plateau.length/4; i++) {
            int pos = (int) (Math.random() * plateau.length);
            Wall w = new Wall();
            w.setPos(pos);
            plateau[pos] = w;
        }
    }
}
