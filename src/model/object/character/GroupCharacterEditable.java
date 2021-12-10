package model.object.character;

import model.player.PlayerAbstract;

public interface GroupCharacterEditable {
    /**
     * Add Character to group.
     * @param p the Character.
     */
    void addCharacter(Character p);

    /**
     * Add Player to group.
     * @param player the Player.
     */
    void addPlayer(PlayerAbstract player);
}
