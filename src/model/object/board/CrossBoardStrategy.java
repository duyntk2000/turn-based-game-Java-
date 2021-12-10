package model.object.board;

import model.object.BoardObject;
import model.object.utility.Wall;

/**
 * The Strategy to create board that has only straight lines.
 */
public class CrossBoardStrategy implements WallStrategy{
    @Override
    public void addWall(BoardObject[] plateau, int size) {
        for (int i = 0 ; i < size; i+=2)
            for (int j = 0 ; j < size; j+=2) {
                int pos = i*size + j;
                Wall w = new Wall();
                w.setPos(pos);
                plateau[pos] = w;
            }
    }
}
