package model.object.weapon;

import model.object.BoardObject;

/**
 * Represent a weapon. It can deal damaged and disappear after usage.
 */
public abstract class Weapon extends BoardObject {
    protected int damage;

    /**
     * Create new Weapon with specified damage.
     * @param damage the damage.
     */
    public Weapon(int damage) {
        this.damage = damage;
    }

    /**
     * Get the damage.
     * @return the damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Set the damage/
     * @param damage the damage.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Weapon disappear after usage.
     */
    public void explode() {
        this.oldPos = this.pos;
        this.pos = -1;
        fireChangement();
    }
}
