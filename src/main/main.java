/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.*;
import model.object.Game;
import model.object.board.*;
import model.object.character.CharacterFactory;
import model.object.utility.History;
import model.object.character.GroupCharacterImpl;
import model.object.character.GroupCharacterToTableModel;
import model.object.character.Character;
import model.player.AI;
import model.player.Human;
import view.*;

import javax.swing.*;
import java.awt.*;

import java.util.Scanner;

/**
 * The main function that in charge of creating all the components then start the game.
 * @author 21811412 NGUYEN Tu Khanh Duy
 */
public class main {
    /**
     * Create group of characters and players.
     * @param total the number of character.
     * @param cb the controller for the players.
     * @param bp the board perspective of each character.
     * @param allBot a boolean, true for creating all AI players.
     * @return the group of players/characters.
     */
    public static GroupCharacterImpl createPlayers(int total, ControllerBoard cb, BoardPrivate bp,boolean allBot) {
        CharacterFactory characterFactory = new CharacterFactory();
        GroupCharacterImpl groupe = new GroupCharacterImpl();
        if (allBot)
            for (int i = 0; i<total; i++) {
                Character p = characterFactory.newCharacter();
                AI ai = new AI(cb,bp,p);
                bp.getBoard().addCharacter(p);
                groupe.addPlayer(ai);
            }
        else
            for (int i = 0; i<total; i++) {
                Character p = characterFactory.newCharacter();
                Human human = new Human(cb,bp,p);
                bp.getBoard().addCharacter(p);
                groupe.addPlayer(human);
            }
        return groupe;
    }

    /**
     * Start the game in mode graphic.
     * @param data the table of data of every character.
     * @param history the History of the game.
     * @param cg the Controller of the game.
     * @param g the Game.
     * @param plv the Board perspective of each character.
     */
    public static void modeGraphique(GroupCharacterToTableModel data, History history, ControllerGame cg, Game g, BoardPrivate plv) {
        ViewGroupCharacterGraphic vg = new ViewGroupCharacterGraphic(data);
        ViewBoardGraphic vpl = new ViewBoardGraphic(plv);

        JFrame frame = new JFrame("Game");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vpl, BorderLayout.CENTER);

        JScrollPane sp = new JScrollPane(vg);
        sp.setPreferredSize(new Dimension(1200, 70));
        frame.add(sp, BorderLayout.NORTH);

        ViewHistoryGraphic vh = new ViewHistoryGraphic(history);
        JScrollPane panel = new JScrollPane(vh);
        panel.setPreferredSize(new Dimension(200, 700));

        ControllerButton button = new ControllerButton(cg);

        frame.add(panel, BorderLayout.EAST);
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        cg.startGame();
    }

    /**
     * Start the game in Command Prompt.
     * @param data the table of data of every character.
     * @param history the History of the game.
     * @param cg the Controller of the game.
     * @param g the Game.
     * @param plv the Board perspective of each character.
     */
    public static void modeCommandLine(GroupCharacterToTableModel data, History history, ControllerGame cg, Game g, BoardPrivate plv) {
        ViewHistory vh = new ViewHistory(history);
        ViewGroupCharacter vgp = new ViewGroupCharacter(data);
        ViewBoard vpl = new ViewBoard(plv);

        cg.startGame();
        while (!g.gameOver()) {
            cg.nextPlayer();
        }
    }

    public static void main(String[] args) {
        /*
        Scanner scan = new Scanner(System.in);


        System.out.println("Enter board size n (nxn) : ");
        int size = scan.nextInt();*/

        //WallStrategy strategy = new RandomStrategy();
        WallStrategy strategy = new CrossBoardStrategy();

        Board b = new Board(15, strategy);
        BoardPrivate bp = new BoardPrivate(b);

        History history = new History();
        ControllerBoard cb = new ControllerBoard(b, history);

        GroupCharacterImpl groupe = createPlayers(3, cb, bp, true);
        GroupCharacterToTableModel data = new GroupCharacterToTableModel(groupe);


        Game game = new Game(bp, groupe);

        ControllerGame cg = new ControllerGame(game);

        modeGraphique(data, history, cg, game, bp);
    }
}
