package model.player;

import controller.ControllerBoard;
import model.object.utility.Action;
import model.object.character.Character;
import model.object.board.BoardPrivate;
import utils.EcouteurModele;

import java.util.ArrayList;

/**
 * An AI player.
 */
public class AI extends PlayerAbstract implements Player, EcouteurModele {

    public AI(ControllerBoard ctl, BoardPrivate pl, Character p) {
        super(ctl, pl, p);
    }

    @Override
    public void chooseAction() throws InterruptedException {
        ArrayList<Action> actions = this.getMoves();
        //actions.forEach(System.out::println);


        int i = (int) (Math.random() * actions.size());
        this.ctl.playAction(p,actions.get(i));
    }
}
