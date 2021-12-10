/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.object.board;

import java.util.ArrayList;

import model.object.BoardObject;
import model.object.weapon.Bomb;
import model.object.weapon.Mine;
import model.object.weapon.Shot;
import model.object.character.Character;
import utils.*;

/**
 * The board of game.
 */
public class Board extends AbstractModeleEcoutable implements EcouteurModele {
    /**
     * the size n of board n x n.
     */
    private final int size;
    /**
     * the strategy to add walls.
     */
    private final WallStrategy wallStrategy;
    /**
     * the board grid, where store objects.
     */
    private BoardObject[] plateau;
    /**
     * the list of bombs.
     */
    private ArrayList<Bomb> bombs;
    /**
     * the map of explosions to display the location of bomb/mine,
     * separated from board grid because explosion and object can be
     * in the same location.
     */
    private Mine[] mapExplosions;
    /**
     * list of direction to help get possible move for character.
     */
    final static int[][] DIRECTION = {{0,-1},{-1,0},{1,0},{0,1},
            {-1,-1},{1,-1},{-1,1},{1,1},
            {0,0}};

    /**
     * Create a new board with specified size and wall-setting strategy.
     * @param size the size n of board n x n.
     * @param wallStrategy the WallStrategy to use.
     * @see WallStrategy
     */
    public Board(int size, WallStrategy wallStrategy) {
        this.wallStrategy = wallStrategy;
        this.size = size;
        this.plateau = new BoardObject[size * size];
        this.mapExplosions = new Mine[size * size];
        this.bombs = new ArrayList<>();
        this.addWall(plateau);
    }

    /**
     * Get the map of all the explosions.
     * @return the array of Mine.
     */
    public Mine[] getMapExplosions() {
        return this.mapExplosions;
    }

    /**
     * Get the list of all the bombs.
     * @return ArrayList of Bomb.
     */
    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }

    /**
     * Get the map of all the objects, excluding explosions.
     * @return the array of BoardObject.
     */
    public BoardObject[] getPlateau() { return this.plateau; }

    /**
     * Get the size.
     * @return the size.
     */
    public int getSize() { return this.size; }

    /**
     * Add a character to the board of object.
     * @param character the Character.
     */
    public void addCharacter(Character character) {
        int pos;
        do {
            pos = (int) (Math.random() * this.size * this.size);
        } while (isObject(pos));
        character.ajoutEcouteur(this);
        this.plateau[pos] = character;
        character.setPos(pos);
        fireChangement();
    }

    /**
     * Setting up walls.
     * @param board the board of object.
     */
    public void addWall(BoardObject[] board) {
        wallStrategy.addWall(plateau, this.size);
        fireChangement();
    }

    /**
     * Add a bomb to the map of explosion and list of bomb.
     * @param b the Bomb.
     * @param pos the position.
     */
    public void addBomb(Bomb b, int pos) {
        b.setPos(pos);
        b.ajoutEcouteur(this);
        this.mapExplosions[pos] = b;
        this.bombs.add(b);
        fireChangement();
    }

    /**
     * Add a mine to the map of explosion.
     * @param m the Mine.
     * @param pos the position.
     */
    public void addMine(Mine m, int pos) {
        m.setPos(pos);
        m.ajoutEcouteur(this);
        this.mapExplosions[pos] = m;
        fireChangement();
    }

    /**
     * Add a shot to the board of object.
     * @param s the Shot.
     * @param pos the position.
     */
    public void addShot(Shot s, int pos) {
        s.ajoutEcouteur(this);
        s.setPos(pos);
        this.plateau[pos] = s;
        fireChangement();
    }

    /**
     * Check if there is an object.
     * @param pos the position.
     * @return true if there is , false otherwise.
     */
    public boolean isObject(int pos) {
        return this.plateau[pos] != null;
    }

    /**
     * Check if there is a character.
     * @param pos the position.
     * @return true if there is , false otherwise.
     */
    public boolean isPersonnage(int pos) {
        return this.plateau[pos] instanceof Character;
    }

    @Override
    public void modeleMisAJour(Object source) {
        if (source instanceof model.object.weapon.Mine) {
            Mine s = (Mine) source;
            if (s.getPos() == -1) {
                this.mapExplosions[s.getOldPos()] = null;
            }
        } else {
            BoardObject s = (BoardObject) source;
            this.plateau[s.getOldPos()] = null;
            if (s.getPos() != -1) {
                this.plateau[s.getPos()] = s;
            }
        }
        fireChangement();
    }

    /**
     * Get all the position around a location.
     * @param n the number of direction.
     * @param location the current location.
     * @return list of all the position.
     */
    public ArrayList<Integer[]> getDirection(int n, int location) {
        ArrayList<Integer[]> res = new ArrayList<>();
        int x = location % this.size;
        int y = location / this.size;

        for (int i = 0; i<n; i++) {
            int x2 = x + DIRECTION[i][0];
            int y2 = y + DIRECTION[i][1];
            if (x2 >= 0 && x2 < this.size && y2 >=0 && y2 < this.size) {
                res.add(new Integer[] {x2,y2,this.size*y2+x2});
            }
        }
        return res;
    }

    /**
     * Remove the position that currently has Wall or Character on it.
     * @param moves list of position.
     * @param shotAble a boolean, true means keep the position that has Character.
     * @return list of possible position for action.
     */
    public ArrayList<Integer[]> filter(ArrayList<Integer[]> moves, boolean shotAble) {
        ArrayList<Integer[]> after = new ArrayList<>();
        moves.forEach(move -> {
            if (this.plateau[move[2]] == null || (shotAble && this.isPersonnage(move[2])))
                after.add(move);

        });
        return after;
    }
}
