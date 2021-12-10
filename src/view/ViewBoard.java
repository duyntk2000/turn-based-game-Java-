/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.object.board.BoardPrivate;
import utils.EcouteurModele;

/**
 * The basic view of the board (plain text).
 */
public class ViewBoard implements EcouteurModele{
    private BoardPrivate plateau;
    
    public ViewBoard(BoardPrivate plateau) {
        this.plateau = plateau;
        plateau.ajoutEcouteur(this);
    }

    /**
     * Update the view.
     * @param source the object that change.
     */
    @Override
    public void modeleMisAJour(Object source) {
        System.out.println("Player " + this.plateau.getCurrentPlayer() + " turn! ");
        StringBuilder head = new StringBuilder(String.format("%5c", 'x'));
        for (int i = 0; i<this.plateau.getSize(); i++) {
            head.append(String.format("%5d", i));
        }
        System.out.println(head);
        for (int i = 0; i<this.plateau.getSize();i++) {
            StringBuilder line = new StringBuilder(String.format("%5d", i));
            for (int j = 0; j<this.plateau.getSize();j++) {
                String v;
                if (this.plateau.getPlateau()[i*this.plateau.getSize()+j] == null)
                    if (this.plateau.getMapExplosions()[i*this.plateau.getSize()+j] == null)
                        v = "_";
                    else
                        v = this.plateau.getMapExplosions()[i*this.plateau.getSize()+j].toString();
                else
                    v = this.plateau.getPlateau()[i*this.plateau.getSize()+j].toString();
                line.append(String.format("%5s", v));
            }
            System.out.println(line);
        }
        System.out.println();
    }
}
