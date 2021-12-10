package view;

import model.object.*;
import model.object.board.BoardPrivate;
import model.object.character.Character;
import model.object.utility.Wall;
import model.object.weapon.Bomb;
import model.object.weapon.Mine;
import model.object.weapon.Shot;
import utils.EcouteurModele;

import javax.swing.*;
import java.awt.*;

/**
 * The view of board in graphic.
 */
public class ViewBoardGraphic extends JPanel implements EcouteurModele {
    protected BoardPrivate pl;

    public ViewBoardGraphic(BoardPrivate plateau) {
        this.pl = plateau;
        plateau.ajoutEcouteur(this);
    }

    /**
     * Draw board : black squares.
     * @param g Graphics.
     */
    public void drawBoard(Graphics g) {
        for (int i = 0 ; i < this.pl.getSize(); i++) {
            for (int j = 0 ; j < this.pl.getSize(); j++) {
                g.fillRect(i*50+1,j*50+1,30,30);
            }
        }
    }

    /**
     * Draw character : white circles.
     * @param g Graphics.
     * @param p the character.
     */
    public void drawPlayer(Graphics g, Character p) {
        g.setColor(Color.white);
        int i = p.getPos()%this.pl.getSize();
        int j = p.getPos()/this.pl.getSize();
        g.fillOval(i*50+1,j*50+1,30,30);
        g.setColor(Color.black);
        g.drawString("P"+ p.getPlayerID(), i*50+10,  j*50+20);
    }

    /**
     * Draw wall : green circles.
     * @param g Graphics.
     * @param w the wall.
     */
    public void drawWall(Graphics g, Wall w) {
        g.setColor(Color.green);
        int i = w.getPos()%this.pl.getSize();
        int j = w.getPos()/this.pl.getSize();
        g.fillOval(i*50+1,j*50+1,30,30);
    }

    /**
     * Draw explosion : red circles with number indicate its countdown.
     * @param g Graphics.
     * @param m the explosion.
     */
    public void drawExplosion(Graphics g, Mine m) {
        g.setColor(Color.red);
        int i = m.getPos()%this.pl.getSize();
        int j = m.getPos()/this.pl.getSize();
        g.fillOval(i*50+1,j*50+1,30,30);
        g.setColor(Color.black);
        if (m instanceof model.object.weapon.Bomb)
            g.drawString(String.valueOf(((Bomb) m).getCountdown()), i*50+10,  j*50+20);
        else
            g.drawString("Infi", i*50+10,  j*50+20);
    }

    /**
     * Draw shot : yellow circles.
     * @param g Graphics.
     * @param s the shot.
     */
    public void drawShot(Graphics g, Shot s) {
        g.setColor(Color.yellow);
        int i = s.getPos()%this.pl.getSize();
        int j = s.getPos()/this.pl.getSize();
        g.fillOval(i*50+1,j*50+1,30,30);
    }

    /**
     * Draw object in general.
     * @param g Graphics.
     * @param o the object.
     */
    public void drawObject(Graphics g,BoardObject o) {
        if (o instanceof Character)
            this.drawPlayer(g,(Character) o);
        else if (o instanceof model.object.weapon.Shot)
            this.drawShot(g,(Shot) o);
        else
            this.drawWall(g,(Wall) o);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawBoard(g);
        Mine[] mapExplosions = this.pl.getMapExplosions();
        BoardObject[] pl = this.pl.getPlateau();
        for (int i = 0; i<this.pl.getSize() * this.pl.getSize();i++) {
            if (pl[i] != null) {
                this.drawObject(g,pl[i]);
            }
            if (mapExplosions[i] != null && mapExplosions[i].visible) {
                this.drawExplosion(g, mapExplosions[i]);
            }
        }
    }

    /**
     * Update view.
     * @param source the object that changed.
     */
    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }
}
