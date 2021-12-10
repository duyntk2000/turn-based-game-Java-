package view;

import model.object.character.GroupCharacterToTableModel;
import utils.EcouteurModele;

import javax.swing.*;

/**
 * View group of character in form of table.
 */
public class ViewGroupCharacterGraphic extends JTable implements EcouteurModele {

    public ViewGroupCharacterGraphic(GroupCharacterToTableModel data) {
        super(data);
        data.ajoutEcouteur(this);
    }

    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }
}
