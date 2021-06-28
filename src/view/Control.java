package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.GameController;

/**
 * This is the Control class. It extends JPanel and implements ActionListener.
 * This will act as a control panel where information about the game will be
 * shown. For example, high scores, points, instructions, etc.
 * 
 * @author Yemin Shou
 */
public class Control extends JPanel implements ActionListener {
	// private buttons and labels
	private JButton menuButton;

	private JLabel pointsLabel;

	/**
	 * Constructor method builds Control panel
	 */
	public Control() {
		super(); // calls super to construct as JPanel

		setLayout(null);
		setBounds(0, 0, 300, 700);
		setBackground(Color.BLACK);

		menuButton = new JButton();
		menuButton.setBounds(getX() + (getWidth() - 200) / 2, 330, 200, 63);
		menuButton.setBackground(Color.BLACK);
		menuButton.setBorder(null);
		menuButton.setIcon(new ImageIcon("menu.gif"));
		menuButton.addActionListener(this);
		add(menuButton);

		// sets points label and border
		pointsLabel = new JLabel(GameView.points + "");
		pointsLabel.setBounds(getX() + (getWidth() - 220) / 2, 50, 220, 110);
		pointsLabel.setFont(new Font("Blue Highway", Font.PLAIN, 36));
		pointsLabel.setForeground(Color.WHITE);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);
		add(pointsLabel);

		JLabel pointsBorder = new JLabel();
		pointsBorder.setBounds(pointsLabel.getBounds());
		pointsBorder.setIcon(new ImageIcon("Images/Border/points.gif"));
		add(pointsBorder);
	}// end constructor

	/**
	 * Updates the pointsLabel using correct formatting of the number
	 */
	public void updatePoints() {
		String points = GameView.points + "";

		if (GameView.points > 999 && GameView.points < 1000000)
			points = points.substring(0, points.length() - 3) + "," + points.substring(points.length() - 3); // adds a
																												// comma
																												// when
																												// appropriate

		pointsLabel.setText(points);
	}// end updatePoints

	/**
	 * Implemented from ActionListener This method will wait for an event to occur
	 * (ex.button being pressed) and will react to that action occurring
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuButton) {
			int prompt = -1;
			if (Timer.on) // if the timer is still on, then user will be prompted
				prompt = JOptionPane.showConfirmDialog(null,
						"Are you sure you would like to return to the menu?\nYour current game will end.",
						"Back to Menu", JOptionPane.YES_NO_OPTION); // confirms that user wants to return to menu
			if (prompt == 0 || !Timer.on) // if timer is not on, or if user said yes, then it will return to the menu
			{
				Timer.on = false; // if player said yes, then timer will be turned off

				new GameController(); // new game will be created
			}

			// else, nothing will happen, game will continue
		}
	}// end actionPerformed
}// end class
