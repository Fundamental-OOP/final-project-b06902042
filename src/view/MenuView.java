package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.GameController;

/**
 * This is the Menu class. It extends JPanel. It contains the basic elements of
 * the start screen at the beginning of the game.
 * 
 * @author Yemin Shou
 */
public class MenuView extends JPanel implements ActionListener {
	// private buttons
	private JButton start;
	private final GameController gameController;

	/**
	 * Constructor method builds Menu panel
	 */
	public MenuView(GameController gameController) {
		super();
		this.gameController = gameController;
		setLayout(null);
		setBounds(0, 0, 880, 700);
		setBackground(new Color(88, 60, 100));

		// title is set
		// JLabel title = new JLabel();
		// title.setLayout(null);
		// title.setBounds(0, 0, 880, 700);
		// title.setIcon(new ImageIcon("logo.jpg"));
		// add(title);

		// buttons are set
		start = new JButton();
		start.setBounds((getWidth() - 350) / 2, 350, 350, 300);
		start.setIcon(new ImageIcon("Images/play.png"));
		start.setBorder(null);
		start.addActionListener(this);
		start.setLayout(null);
		add(start);
	}
	public void paintComponent(Graphics g){
		Image img = (new ImageIcon("Images/candy.jpg")).getImage();
		g.drawImage(img,0,0,880,700,0,0 ,img.getWidth(null),img.getHeight(null),null);
		repaint();
	}
	@Override
	public boolean isOptimizedDrawingEnabled() {
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			this.gameController.startGame();
		}
	}
}
