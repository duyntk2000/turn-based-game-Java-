package view;

import model.object.character.GroupCharacterToTableModel;
import utils.EcouteurModele;

/**
 * View group of character in form of plain text.
 */
public class ViewGroupCharacter implements EcouteurModele {
    private GroupCharacterToTableModel data;

    public ViewGroupCharacter(GroupCharacterToTableModel data) {
        this.data = data;
        data.ajoutEcouteur(this);
    }

    @Override
    public void modeleMisAJour(Object source) {
        String header = "ID        Energy    Bombs     Mines     Shots     Armors    Shielded  ";
        System.out.println(header);
        for (int i = 0; i < data.getRowCount(); i++) {
            StringBuilder v = new StringBuilder();
            for (int j = 0; j < data.getColumnCount(); j++) {
                v.append(String.format("%-10s", data.getValueAt(i, j)));
            }
            System.out.println(v);
        }

    }
}
