package model.object.character;

/**
 * A Character builder.
 */
public class CharacterFactory {
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
     * Create a new CharacterFactory with default configuration.
     */
    public CharacterFactory() {
        this.energy = 20;
        this.bombs = 4;
        this.mines = 4;
        this.shots = 5;
        this.armors = 3;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setArmors(int armors) {
        this.armors = armors;
    }

    /**
     * Return a new instance of Character with factory specification.
     * @return a new Character.
     */
    public Character newCharacter() {
        return new Character(this.energy, this.bombs, this.mines, this.shots, this.armors);
    }
}
