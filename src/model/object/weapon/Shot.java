/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.object.weapon;

/**
 * A shot.
 */
public class Shot extends Weapon {
    private final int x;
    private final int y;

    /**
     * Create new shot with specified direction.
     * @param x x-coordinate of direction.
     * @param y y-coordinate of direction.
     */
    public Shot(int x, int y) {
        super(2);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Flying 1 case from the position in its direction.
     * @param size the size of the board.
     */
    public void fly(int size) {
        this.oldPos = this.pos;
        this.pos += size * y + x;
        fireChangement();
    }
    
    @Override
    public String toString() {
        return "s";
    }
    
}
