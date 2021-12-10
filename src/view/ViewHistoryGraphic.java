package view;

import model.object.utility.History;
import utils.EcouteurModele;

import javax.swing.*;

/**
 * View history in form of a board of action.
 */
public class ViewHistoryGraphic extends JTextArea implements EcouteurModele {
    private final static String newline = "\n";

    public ViewHistoryGraphic(History history) {
        history.ajoutEcouteur(this);
        this.setEditable(false);
    }

    @Override
    public void modeleMisAJour(Object source) {
        History history = (History) source;
        this.append(history.getLastMove() + newline);
        this.repaint();
        this.setCaretPosition(this.getDocument().getLength());
    }
}
