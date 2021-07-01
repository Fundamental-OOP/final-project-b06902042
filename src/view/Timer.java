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

	public static void setBar(int seconds) {
		on = true;
		bar = new JProgressBar(0, seconds);
		bar.setValue(bar.getMaximum());
		bar.setStringPainted(true);

		String zero = "";
		if (bar.getValue() % 60 < 10)
			zero = "0";
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
				gameView.countdown.setVisible(true);

				for (int i = 0; i < 4; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}

					String text = (i == 0) ? "2" : (i == 1) ? "1" : "GO!";

					gameView.countdown.setText(text);
				}

				gameView.boardView.setVisible(true);

				while (bar.getValue() > 0 && on) {
					bar.setValue(bar.getValue() - 1);

					String zero = "";
					if (bar.getValue() % 60 < 10)
						zero = "0";

					bar.setString((bar.getValue() / 60) + ":" + zero + (bar.getValue() % 60));

					if (bar.getValue() < 11)
						bar.setForeground(Color.RED);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}

				if (on) {
					gameView.over(true);
					on = false;
				}

			}
		});
	}
}
