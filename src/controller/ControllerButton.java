package controller;

import javax.swing.*;

/**
 * Controller of the game in form of a button.
 */
public class ControllerButton extends JButton {
    /**
     * Create a new button that change turn when clicked.
     * @param cg the ControllerGame to use.
     */
    public ControllerButton(ControllerGame cg) {
        super("Suivant");
        this.addActionListener(e -> {
            SwingWorker sw = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    cg.nextPlayer();
                    return null;
                }
            };
            sw.execute();
        });
    }
}
