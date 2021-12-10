package model.player;

import controller.ControllerBoard;
import model.object.utility.Action;
import model.object.character.Character;
import model.object.board.BoardPrivate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Human player.
 */
public class Human extends PlayerAbstract {

    public Human(ControllerBoard ctl, BoardPrivate pl, Character p) {
        super(ctl, pl, p);
    }

    @Override
    public void chooseAction() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Action> actions = this.getMoves();
        actions.forEach(System.out::println);

        int i = scan.nextInt();

        ctl.playAction(p,actions.get(i));
    }
}
