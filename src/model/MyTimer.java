package model;
import java.awt.*;
import javax.swing.*;

import controller.GameController;
import view.BoardView;

import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class MyTimer {
    Timer timer;
    public int sizeVelocity = -9;
    GameController gameController;
    public MyTimer(GameController ctrl)  {
        gameController = ctrl;
        timer = new Timer(0, act1);
        // try {
        //     Thread.sleep(3000);
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        timer.start();
    }
    ActionListener act1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        sizeVelocity += 2;
        if (sizeVelocity > 40){
            sizeVelocity = -9;
            timer.stop();
        }
        gameController.repaintBoard(sizeVelocity);
        }
        
    } ;  
 
}