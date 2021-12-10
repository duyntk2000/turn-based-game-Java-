package model.object.weapon;

/**
 * An explosion with no countdown.
 */
public class Mine extends Weapon{
    private final int ownerID;
    public boolean visible;

    public Mine(int damage, int ownerID) {
        super(damage);
        this.ownerID = ownerID;
    }

    public int getOwnerID() { return this.ownerID; }

    @Override
    public String toString() {
        return "m";
    }
}
