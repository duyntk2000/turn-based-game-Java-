package model.object.character;

import model.player.PlayerAbstract;
import utils.AbstractModeleEcoutable;
import utils.EcouteurModele;

import java.util.ArrayList;

/**
 * The implementation of GroupCharacter.
 */
public class GroupCharacterImpl extends AbstractModeleEcoutable implements GroupCharacter, GroupCharacterEditable, EcouteurModele {
    /**
     * list of players.
     */
    private ArrayList<PlayerAbstract> players;
    /**
     * list of characters.
     */
    private ArrayList<Character> groupe;

    public GroupCharacterImpl() {
        this.players = new ArrayList<>();
        this.groupe = new ArrayList<>();
    }

    @Override
    public int getTotalCharacters() {
        return this.groupe.size();
    }

    @Override
    public Character getCharacter(int index) {
        return this.groupe.get(index);
    }

    /**
     * Get groupe of players.
     * @return the groupe of players.
     */
    public ArrayList<PlayerAbstract> getPlayers() {
        return players;
    }

    /**
     * Get groupe of character.
     * @return the groupe of character.
     */
    public ArrayList<Character> getGroupe() {
        return groupe;
    }

    @Override
    public void addCharacter(Character p) {
        this.groupe.add(p);
        p.ajoutEcouteur(this);
        fireChangement();
    }

    @Override
    public void addPlayer(PlayerAbstract player) {
        this.players.add(player);
        this.addCharacter(player.getCharacter());
    }

    @Override
    public void modeleMisAJour(Object source) {
        Character p = (Character) source;
        if (p.getPos() == -1) {
            this.groupe.remove(p);
        }
        fireChangement();
    }
}
