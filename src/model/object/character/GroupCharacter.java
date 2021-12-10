package model.object.character;

public interface GroupCharacter {
    /**
     * Get number of characters.
     * @return number of characters.
     */
    int getTotalCharacters();

    /**
     * Get the Character with specified id.
     * @param index the id.
     * @return the Character.
     */
    Character getCharacter(int index);
}
