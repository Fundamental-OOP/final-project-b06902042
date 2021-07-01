package view;

import java.awt.*;
import javax.swing.*;

import controller.*;

public class GameView extends JFrame {
    public BoardView boardView;
    private Control controlView;
    private MenuView menuView;
    private Timer timer;
    public static int points;

    public JLabel countdown;
    private final GameController gameController;

    public GameView(GameController gameController) {
        super("Bejeweled");
        this.gameController = gameController;
    }

    public void launch() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(880, 700);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.BLACK);

        
        menuView = new MenuView(this.gameController);
        container.add(menuView);
        setVisible(true);
    }

    public void startGame() {
        Container container = getContentPane();
        container.remove(menuView);
        boardView = new BoardView(this.gameController);
        boardView.addActionListener();
        boardView.setVisible(false);
        container.add(boardView);

        JLabel topBorder = new JLabel();
        topBorder.setBounds(boardView.getX() + (boardView.getWidth() - 550) / 2, boardView.getY() - 30, 535, 30);
        topBorder.setIcon(new ImageIcon("Images/Border/grid.gif"));
        container.add(topBorder);

        JLabel timerBorder = new JLabel();
        timerBorder.setBounds(boardView.getX() + (boardView.getWidth() - 550) / 2,
                boardView.getY() + boardView.getHeight(), 550, 40);
        timerBorder.setIcon(new ImageIcon("Images/Border/timer.gif"));
        container.add(timerBorder);

        timer = new Timer(this);
        timer.setBar(60);
        timer.bar.setVisible(true);
        timer.bar.setBounds(boardView.getX() + (boardView.getWidth() - 465) / 2,
                boardView.getY() + boardView.getHeight() + 7, 465, 26);
        container.add(timer.bar);

        controlView = new Control();
        controlView.setVisible(true);
        container.add(controlView);

        countdown = new JLabel("3");
        countdown.setBounds(boardView.getX() + (boardView.getWidth() - 300) / 2,
                boardView.getY() + (boardView.getHeight() - 150) / 2, 300, 150);
        countdown.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 130));
        countdown.setForeground(new Color(225, 225, 150));
        countdown.setHorizontalAlignment(JLabel.CENTER);
        countdown.setVisible(true);
        container.add(countdown);

        repaint();
        timer.start();
    }

    public void over() {
        Container container = getContentPane();
        controlView.submitScore();
        container.removeAll();
        container.add(menuView);
        new LeaderBoard();
        repaint();
    }

    public void repaintBoard(int size) {
        boardView.repaintBoard(size);
    }

    public void updatePoint(){
        controlView.updatePoints();
    }
}
