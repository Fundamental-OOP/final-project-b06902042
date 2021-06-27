package view;

import java.awt.*;
import javax.swing.*;

import java.util.concurrent.*;

public class Timer {
	private GameView gameView;
	private static ExecutorService gameExecutor = Executors.newSingleThreadExecutor();
	private static Future<?> gameTask;

	public static JProgressBar bar;

	public static boolean on = true;

	public Timer(GameView gameView) {
		this.gameView = gameView;
	}

	/**
	 * Sets up the bar JProgressBar
	 * 
	 * @param seconds Sets the number of seconds the timer will count down from
	 */
	public static void setBar(int seconds) {
		on = true; // every time you reset the bar, on becomes true again so that timer will work

		bar = new JProgressBar(0, seconds); // sets minimum and maximum value of the bar
		bar.setValue(bar.getMaximum()); // sets the starting value to the maximum
		bar.setStringPainted(true);

		String zero = "";
		if (bar.getValue() % 60 < 10)
			zero = "0"; // adds a zero if the number of seconds (disregarding whole minutes) is less
						// than 10

		bar.setString((bar.getValue() / 60) + ":" + zero + (bar.getValue() % 60));
		bar.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 22));
		bar.setBorderPainted(false);
		bar.setOpaque(true);
		bar.setBackground(new Color(60, 23, 27));
		bar.setForeground(new Color(0, 82, 118));
	}

	public void start() {
		if (gameTask != null)
			gameTask.cancel(true);

		gameTask = gameExecutor.submit(new Runnable() {
			public void run() {
				gameView.countdown.setVisible(true); // the countdown becomes visible

				for (int i = 0; i < 4; i++) // loops 3 times
				{
					try {
						Thread.sleep(1000); // delays 1 second
					} catch (InterruptedException e) {
					}

					String text = (i == 0) ? "2" : (i == 1) ? "1" : "GO!"; // this is sort of a simplified if-else
																			// statement where if i == 0 then text will
																			// be "2" and if i == 1 then text is "1",
																			// else text is "GO!"

					gameView.countdown.setText(text); // sets the countdown to the text
				}

				gameView.boardView.setVisible(true); // after the countdown is over, board is visible and the game will
														// start

				while (bar.getValue() > 0 && on) // while the bar value is greater than 0
				{
					bar.setValue(bar.getValue() - 1); // bar value will decrease by 1

					String zero = "";
					if (bar.getValue() % 60 < 10)
						zero = "0";

					bar.setString((bar.getValue() / 60) + ":" + zero + (bar.getValue() % 60)); // shows a string on the
																								// bar depicting the
																								// time left

					if (bar.getValue() < 11)
						bar.setForeground(Color.RED);

					try {
						Thread.sleep(1000); // a delay of one second (1000 milliseconds) will occur
					} catch (InterruptedException e) {
					}
				}

				if (on) // if the boolean is still on when the while loop is over
				{
					gameView.over(); // game is over because the game was played until timer reached 0
					on = false; // otherwise, the user may have quit the game
				}

			}
		});
	}

	// public static void gridFunctions(Jewel s, Jewel p) {
	// source = s; // this is necessary because in the new Runnable class below, it
	// can only access
	// // the static Jewels in the Timer class and can't access s and p
	// pressed = p;

	// // this is the little piece of code again that allows thread.sleep to work
	// if (gameTask2 != null)
	// gameTask2.cancel(true);

	// // the new Runnable that is necessary for thread.sleep to work
	// gameTask2 = gameExecutor2.submit(new Runnable() {
	// public void run() {
	// Game.board.markLine(source, Game.board.checkThree(source)); // marks the
	// group of 3 or more if it is the
	// // second source Jewel
	// Game.board.markLine(pressed, Game.board.checkThree(pressed)); // marks the
	// group of 3 or more if it is
	// // the firstPressed Jewel

	// Game.board.clearGrid(); // clears the marked group
	// Game.board.refillGrid(); // refills the cleared Jewels with new ones
	// Game.control.updatePoints();

	// while (Game.board.checkGrid()) // checks the entire grid after the Jewels
	// have been moved and while
	// // there is a new group of 3 on the grid, it will mark it
	// {
	// Game.points += 500; // if there are extra groups on the grid after the
	// swapped ones were cleared,
	// // cascade bonus points are given
	// Game.board.clearGrid(); // the grid will be cleared
	// Game.board.refillGrid(); // the grid will be refilled with new Jewels
	// Game.control.updatePoints();
	// }
	// }// end run method
	// });// end new Runnable
	// }
}
