package model.object.character;

import utils.AbstractModeleEcoutable;
import utils.EcouteurModele;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


/**
 * An adapter from GroupeCharacter to TableModel.
 */
public class GroupCharacterToTableModel extends AbstractModeleEcoutable implements TableModel, EcouteurModele {
    /**
     * group character data.
     */
    protected GroupCharacterImpl groupe;

    public GroupCharacterToTableModel(GroupCharacterImpl groupe) {
        this.groupe = groupe;
        groupe.ajoutEcouteur(this);
    }

    @Override
    public int getRowCount() {
        return this.groupe.getTotalCharacters();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Energy";
            case 2:
                return "Bombs";
            case 3:
                return "Mines";
            case 4:
                return "Shots";
            case 5:
                return "Armors";
            default:
                return "Shielded";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Character p = this.groupe.getCharacter(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPlayerID();
            case 1:
                return p.getEnergy();
            case 2:
                return p.getBombs();
            case 3:
                return p.getMines();
            case 4:
                return p.getShots();
            case 5:
                return p.getArmors();
            default:
                return p.getIsProtected();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

    @Override
    public void modeleMisAJour(Object source) {
        fireChangement();
    }

}
