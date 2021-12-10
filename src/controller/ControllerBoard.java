package controller;

import java.util.ArrayList;

import model.object.BoardObject;
import model.object.board.Board;
import model.object.character.Character;
import model.object.utility.Action;
import model.object.utility.History;
import model.object.weapon.Bomb;
import model.object.weapon.Mine;
import model.object.weapon.Shot;
import model.object.weapon.Weapon;

/**
 * Controller for class Board.
 */
public class ControllerBoard {
    /**
     * the Board
     */
    protected Board board;
    /**
     * the History
     */
    protected History history;

    /**
     * Create a new ControllerBoard with the specified board and history.
     * @param board The Board to use.
     * @param history An History instance to record the events.
     */
    public ControllerBoard(Board board, History history) {

        this.board = board;
        this.history = history;
    }

    /**
     * Move the specified character to a position.
     * @param p The Character to move.
     * @param x The x-coordinate or column index of the board.
     * @param y The y-coordinate or row index of the board.
     */
    public void move(Character p, int x, int y) {
        int pos = this.board.getSize() * y + x;
        Mine e = this.isExplosion(pos);
        if (e != null) {
            p.move(pos);
            this.stepOnExplosion(e);
        }
        p.move(pos);
    }

    /**
     * Make the specified character plant a bomb to a position.
     * @param p The Character to use.
     * @param x The x-coordinate or column index of the board.
     * @param y The y-coordinate or row index of the board.
     */
    public void setBomb(Character p, int x, int y) {
        int pos = this.board.getSize() * y + x;
        p.useBomb();
        Bomb b = new Bomb(p.getPlayerID());
        board.addBomb(b,pos);
    }

    /**
     * Make the specified character plant a mine to a position.
     * @param p The Character to use.
     * @param x The x-coordinate or column index of the board.
     * @param y The y-coordinate or row index of the board.
     */
    public void setMine(Character p, int x, int y) {
        int pos = this.board.getSize() * y + x;
        p.useMine();
        Mine m = new Mine(2,p.getPlayerID());
        board.addMine(m,pos);
    }

    /**
     * Reduce the countdown of all bombs and make it explode if needed.
     */
    public void checkBomb() {
        ArrayList<Bomb> bombs = board.getBombs();
        ArrayList<Bomb> toRemove = new ArrayList<>();
        bombs.forEach(bomb -> {
            bomb.countdown();
            if (bomb.getCountdown() == 0) {
                this.explode(bomb);
                toRemove.add(bomb);
            }
        });
        bombs.removeAll(toRemove);
    }

    /**
     * Bomb/Mine exploded. Remove the bomb/mine from the board's map/bomb-list. Deal damage to Character surrounding.
     * @param m The Bomb/Mine that exploded.
     */
    public void explode(Mine m) {
        this.history.addAction(new Action(6,m.getPos()%board.getSize(), m.getPos()/ board.getSize(), m.getOwnerID()));
        int pos = m.getPos();
        BoardObject[] plateau = board.getPlateau();
        ArrayList<Integer[]> area = this.board.getDirection(9, pos);
        area.forEach(po -> {
            if (plateau[po[2]] instanceof Character) {
                    Character p = (Character) plateau[po[2]];
                    this.dealDamage(m, p);
            }
        });
        m.explode();
    }

    /**
     * Character got hit by a Weapon. Energy get lowered.
     * @param w The Weapon.
     * @param p The Character.
     */
    public void dealDamage(Weapon w, Character p) {
        p.getDamaged(w.getDamage());
    }

    /**
     * The Shot hits a Character/Wall.
     * @param s The Shot.
     * @param pos The position of the collision.
     */
    public void shotHit(Shot s, int pos) {
        if (board.getPlateau()[pos] instanceof Character) {
            Character p = (Character) board.getPlateau()[pos];
            this.dealDamage(s, p);
        }
    }

    /**
     * Make the specified character shot in a direction.
     * @param p The Character.
     * @param x The x-coordinate of the direction.
     * @param y The y-coordinate of the direction.
     * @throws InterruptedException .
     */
    public void shoot(Character p, int x, int y) throws InterruptedException {
        p.shoot();
        int distance;
        int xy;
        int current;
        int xs = x - p.getPos()%this.board.getSize();
        int ys = y - p.getPos()/this.board.getSize();
        System.out.println(xs + " " + ys);
        int pos = y*this.board.getSize() + x;
        Shot s = new Shot(xs,ys);

        if (xs == 0) {
            distance = board.getSize();
            xy = ys;
            current = y;
        } else {
            distance = 1;
            xy = xs;
            current = x;
        }
        if (this.board.isPersonnage(pos)) {
            this.shotHit(s, pos);
        } else {
            board.addShot(s, pos);
            while (current > 0 && current < this.board.getSize() - 1) {
                Thread.sleep(500);
                if (this.board.isObject(s.getPos()+distance*xy)) {
                    this.shotHit(s, s.getPos()+distance*xy);
                    s.explode();
                    return;
                }
                s.fly(board.getSize());
                current += xy;
            }
        }
        Thread.sleep(500);
        s.explode();
    }

    /**
     * Character skips a turn, not consuming energy.
     * @param p The Character.
     */
    public void rest(Character p) {
        p.rest();
    }

    /**
     * Character uses an armor, protected from next damage.
     * @param p The Character
     */
    public void useArmor(Character p) {
        p.useArmor();
    }

    /**
     * The specified character do an action chosen by the player(Human/AI).
     * @param p The Character.
     * @param action The Action.
     * @see Action
     * @throws InterruptedException .
     */
    public void playAction(Character p, Action action) throws InterruptedException{
        this.history.addAction(action);
        switch (action.getAction()) {
            case 0:
                this.move(p,action.getPosX(), action.getPosY());
                break;
            case 1:
                this.setBomb(p,action.getPosX(), action.getPosY());
                break;
            case 2:
                this.setMine(p,action.getPosX(), action.getPosY());
                break;
            case 3:
                this.shoot(p,action.getPosX(), action.getPosY());
                break;
            case 4:
                this.useArmor(p);
                break;
            case 5:
                this.rest(p);
                break;
        }
        this.checkBomb();
    }

    /**
     * Check if there is an explosion.
     * @param pos The position to check.
     * @return Mine object if founded , null otherwise.
     */
    public Mine isExplosion(int pos) {
        if (board.getMapExplosions()[pos] != null) {
            return board.getMapExplosions()[pos];
        }
        return null;
    }

    /**
     * Character steps on an explosion, trigger it to explode.
     * @param m The Mine object.
     */
    public void stepOnExplosion(Mine m) {
        if (m instanceof model.object.weapon.Bomb)
            this.board.getBombs().remove((Bomb) m);
        this.explode(m);
    }
    
}
