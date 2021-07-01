package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.GameController;

public class BoardView extends JPanel implements ActionListener {
	private int boardSize = 10;
	public int sizeVelocity = 0;
	private GameController gameController;
	private GemView grid[][] = new GemView[boardSize][boardSize];

	public BoardView(GameController gameController) {
		this.gameController = gameController;
		setLayout(null);
		setBounds(300, 90, 535, 500);
		setBackground(Color.BLACK);
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				grid[x][y] = new GemView();
				grid[x][y].setBounds(x * 50 + ((getWidth() - boardSize * 50) / 2), y * 50, 50, 50);
				grid[x][y].setMyXY(x, y);
				grid[x][y].setIcon(new ImageIcon(gameController.getGridImagePath(x, y)));
				add(grid[x][y]);
			}
		}
	}

	public void addActionListener() {
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				grid[x][y].addActionListener(this);
			}
		}
	}

	public void removeActionListener() {
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				grid[x][y].removeActionListener(this);
			}
		}
	}

	public void repaintBoard(int size) {
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				if (gameController.getRemoveFlag(x, y) == true) {
					Image img = new ImageIcon("Images/bomb.png").getImage();
					Image newimg = img.getScaledInstance(size + 10, size + 10, java.awt.Image.SCALE_SMOOTH);
					grid[x][y].setIcon(new ImageIcon(newimg));
					grid[x][y].setBackground(Color.BLACK);
					grid[x][y].setOpaque(true);
					grid[x][y].setBorderPainted(false);
				} else {
					grid[x][y].setIcon(new ImageIcon(gameController.getGridImagePath(x, y)));
					grid[x][y].setButtonBorder(gameController.getSetButtonBorder(x, y));
				}
			}
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GemView source = (GemView) e.getSource();
		gameController.boardPerformClick(source);
		repaintBoard(0);
	}
}
