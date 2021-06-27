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
		JLabel title = new JLabel();
		title.setBounds((getWidth() - 600) / 2, 20, 600, 250);
		title.setIcon(new ImageIcon("logo.gif"));
		add(title);

		// buttons are set
		start = new JButton();
		start.setBounds((getWidth() - 350) / 2, 280, 350, 300);
		start.setIcon(new ImageIcon("play.gif"));
		start.setBorder(null);
		start.addActionListener(this);
		add(start);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			this.gameController.startGame();
		}
	}
}
