package model.object.weapon;


/**
 * An explosion with countdown.
 */
public class Bomb extends Mine {
    private int countdown = 4;

    public Bomb(int ownerID) {
        super(2, ownerID);
    }

    public int getCountdown() {
        return this.countdown;
    }

    public void countdown() {
        this.countdown --;
        fireChangement();
    }

    @Override
    public String toString() {
        return "b" + this.countdown;
    }
}
