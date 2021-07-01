package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.GameController;
import model.IO;

/**
 * This is the Control class. It extends JPanel and implements ActionListener.
 * This will act as a control panel where information about the game will be
 * shown. For example, high scores, points, instructions, etc.
 * 
 * @author Yemin Shou
 */
public class Control extends JPanel implements ActionListener {
	// private buttons and labels
	private JButton pauseButton;
	private GameView gameview;
	private JLabel pointsLabel;

	/**
	 * Constructor method builds Control panel
	 */
	public Control(GameView gameView) {
		super(); // calls super to construct as JPanel
		this.gameview = gameView;
		setLayout(null);
		setBounds(0, 0, 300, 700);
		setBackground(Color.BLACK);

		pauseButton = new JButton();
		pauseButton.setBounds(getX() + (getWidth() - 100) / 2, 330, 100, 100);
		pauseButton.setBackground(Color.BLACK);
		pauseButton.setBorder(null);
		pauseButton.setIcon(new ImageIcon("Images/icon/pause.jpg"));
		pauseButton.addActionListener(this);
		add(pauseButton);

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

	public void submitScore()
	{
		String name = (String) JOptionPane.showInputDialog(null, "GAME OVER. \n You scored " + GameView.points + " points. \nSubmit your score into the leaderboard to compare with other players!\nEnter your name to submit your score:", 
		"Submit Your Score", JOptionPane.PLAIN_MESSAGE, null, null, "John");	//opens dialog

		if(name != null)
		{
			IO.openWriteFile("Highscore.txt");
			IO.writeln(name);
			IO.writeln(GameView.points + "");
			IO.closeWriteFile();
			new LeaderBoard();
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object[] options = {"Restart", "Continue"};
		if (e.getSource() == pauseButton) {
			 // if the timer is still on, then user will be prompted
			int prompt = JOptionPane.showOptionDialog(null,
						"Are you sure you would like to return to the menu?\nYour current game will end.",
						"Pause", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); // confirms that user wants to return to menu
			if (prompt == 0) // if timer is not on, or if user said yes, then it will return to the menu
			{ 
				gameview.over(false);
				Timer.on = false;
			}

			// else, nothing will happen, game will continue
		}
	}// end actionPerformed
}// end class
