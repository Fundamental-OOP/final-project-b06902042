package view;

import javax.swing.*;
import model.IO;
import java.awt.*;

public class LeaderBoard extends JFrame {

	private int[] scores;
	private int topScores = 10;
	private String[] names;
	private JLabel[] nameLabel = new JLabel[10];
	private JLabel[] scoreLabel = new JLabel[10];

	public LeaderBoard() {
		super("Leaderboard");

		setSize(400, 650);
		setResizable(false);
		setLayout(null);

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(60, 23, 27));
		JLabel highScores = new JLabel("High Scores");
		highScores.setBounds(getX(), 20, getWidth(), 50);
		highScores.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
		highScores.setHorizontalAlignment(JLabel.CENTER);
		highScores.setForeground(Color.WHITE);
		c.add(highScores);

		getHighScore();
		if (scores.length < topScores)
			topScores = scores.length;

		for (int i = 0; i < topScores; i++) {
			nameLabel[i] = new JLabel(i + 1 + ".  " + names[i]);
			nameLabel[i].setBounds(50, 80 + i * 30, 200, 50);
			nameLabel[i].setFont(new Font("Blue Highway", Font.PLAIN, 28));
			nameLabel[i].setForeground(Color.WHITE);
			c.add(nameLabel[i]);

			scoreLabel[i] = new JLabel(stringScore(scores[i]));
			scoreLabel[i].setBounds(200, 80 + i * 30, 150, 50);
			scoreLabel[i].setFont(new Font("Blue Highway", Font.PLAIN, 28));
			scoreLabel[i].setForeground(Color.WHITE);
			scoreLabel[i].setHorizontalAlignment(JLabel.RIGHT);
			c.add(scoreLabel[i]);
		}
		setVisible(true);
	}

	private void getHighScore() {
		int numLines = 0;
		IO.openReadFile("Highscore.txt");
		while (IO.readLine() != null) {
			numLines++;
		}
		names = new String[numLines / 2];
		scores = new int[numLines / 2];
		IO.closeReadFile();
		IO.openReadFile("Highscore.txt");
		for (int i = 0; i < scores.length; i++) {
			names[i] = IO.readLine();
			scores[i] = Integer.parseInt(IO.readLine());
		}
		IO.closeReadFile();
		sort();
	}

	private void sort() {
		int len = scores.length;

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (scores[j] > scores[i]) {
					int temp = scores[i];
					scores[i] = scores[j];
					scores[j] = temp;
					String temp2 = names[i];
					names[i] = names[j];
					names[j] = temp2;
				}
			}
		}
	}

	public String stringScore(int score) {
		String s = score + "";

		if (score > 999 && score < 1000000)
			s = s.substring(0, s.length() - 3) + "," + s.substring(s.length() - 3);

		return s;
	}
}
