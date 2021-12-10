package view;

import model.object.utility.History;
import utils.EcouteurModele;

/**
 * View history in form of each action.
 */
public class ViewHistory implements EcouteurModele {
    private History history;

    public ViewHistory(History history) {
        this.history = history;
        history.ajoutEcouteur(this);
    }

    @Override
    public void modeleMisAJour(Object source) {
        System.out.println(history.getLastMove());
    }
}
