/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.object.character;

import java.util.ArrayList;

import model.object.BoardObject;
import model.object.utility.Action;

/**
 * The Character of the game
 */
public class Character extends BoardObject {
    /**
     * player id.
     */
    private final int playerID;
    /**
     * the number of bombs.
     */
    private int bombs;
    /**
     * the number of mines.
     */
    private int mines;
    /**
     * the number of shots.
     */
    private int shots;
    /**
     * the energy.
     */
    private int energy;
    /**
     * the number of armors.
     */
    private int armors;
    /**
     * character is using armor or not.
     */
    private boolean isProtected;
    /**
     * global int to help determine player id.
     */
    public static int id = -1;

    /**
     * Create a new Character with specified energy, number of bombs/mines/armors/shots.
     * @param energy the number of energy.
     * @param bombs the number of bombs.
     * @param mines the number of mines.
     * @param shots the number of shots.
     * @param armors the number of armors.
     */
    public Character(int energy, int bombs, int mines, int shots, int armors) {
        Character.id ++;

        this.playerID = Character.id;
        this.energy = energy;
        this.bombs = bombs;
        this.mines = mines;
        this.shots = shots;
        this.armors = armors;
    }

    /**
     * Get character id.
     * @return the id.
     */
    public int getPlayerID() {return this.playerID; }

    /**
     * Get character energy.
     * @return the energy.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Get character bombs.
     * @return the bombs.
     */
    public int getBombs() {
        return bombs;
    }

    /**
     * Get character mines.
     * @return the mines.
     */
    public int getMines() {
        return mines;
    }

    /**
     * Get character shots.
     * @return the shots.
     */
    public int getShots() {
        return shots;
    }

    /**
     * Get character armors.
     * @return the armors.
     */
    public int getArmors() {
        return armors;
    }

    /**
     * Check if character is using armor.
     * @return a boolean.
     */
    public boolean getIsProtected() { return this.isProtected; }
    
    @Override
    public String toString() {
        return "P"+this.playerID;
    }

    /**
     * Check if character is dead.
     * @return a boolean.
     */
    public boolean isDead() {
        return this.energy <= 0;
    }

    /**
     * Character dies.
     */
    public void die() {
        this.setOldPos(this.getPos());
        this.setPos(-1);
        fireChangement();
        this.ecouteurs.clear();

    }

    /**
     * Character get damaged, lower the energy.
     * @param dmg the damage.
     */
    public void getDamaged(int dmg) {
        if (this.isProtected) {
            this.isProtected = false;
            fireChangement();
        } else {
            this.energy -= dmg;
            fireChangement();
            if (this.isDead()) 
                this.die();
        }
    }

    /**
     * Character move to new position.
     * @param pos the position.
     */
    public void move(int pos) {
        this.setOldPos(this.getPos());
        this.setPos(pos);
        this.energy --;
        fireChangement();
    }

    /**
     * Character uses shot.
     */
    public void shoot() {
        this.shots --;
        this.energy --;
        fireChangement();
    }

    /**
     * Character uses bomb.
     */
    public void useBomb() {
        this.bombs --;
        this.energy --;
        fireChangement();
    }

    /**
     * Character uses mine.
     */
    public void useMine() {
        this.mines --;
        this.energy --;
        fireChangement();
    }

    /**
     * Character skips turn.
     */
    public void rest() {
        fireChangement();
    }

    /**
     * Character uses armor.
     */
    public void useArmor() {
        this.energy --;
        this.armors --;
        this.isProtected = true;
        fireChangement();
    }

    /**
     * Get the possible actions base on resources.
     * @return list of action.
     */
    public ArrayList<Integer> getActionPossible() {
        ArrayList<Integer> action = new ArrayList<>();
        if (this.energy == 0) {
            action.add(Action.REST_REF);
            return action;
        }
        action.add(Action.MOVE_REF);
        if (this.bombs > 0 )
            action.add(Action.BOMB_REF);
        if (this.mines > 0 )
            action.add(Action.MINE_REF);
        if (this.shots > 0 )
            action.add(Action.SHOT_REF);
        if (this.armors > 0)
            action.add(Action.ARMOR_REF);
        action.add(Action.REST_REF);
        return action;
    }

}
