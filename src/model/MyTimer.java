package model;

import javax.swing.*;
import controller.GameController;
import java.awt.event.*;

public class MyTimer {
    Timer timer;
    public int sizeVelocity = -9;
    GameController gameController;

    public MyTimer(GameController ctrl) {
        gameController = ctrl;
        timer = new Timer(0, act1);
        timer.start();
    }

    ActionListener act1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sizeVelocity += 2;
            if (sizeVelocity > 40) {
                sizeVelocity = -9;
                timer.stop();
            }
            gameController.repaintBoard(sizeVelocity);
        }

    };

}