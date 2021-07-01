package controller;

import java.awt.*;
import java.util.concurrent.*;

import javax.swing.*;

import model.*;
import view.BoardView;
import view.GameView;

public class GameController {
	private GameView gameView = new GameView(this);
	private Board board;
	private ExecutorService gameExecutor2 = Executors.newSingleThreadExecutor();
	private Future<?> gameTask2;

	public void start() {
		gameView.launch();
	}

	public void startGame() {
		board = new Board(this);
		gameView.startGame();
	}

	public String getGridImagePath(int x, int y) {
		return board.getGridImagePath(x, y);
	}

	public boolean getSetButtonBorder(int x, int y) {
		return board.getSetButtonBorder(x, y);
	}

	public void boardPerformClick(GridBox source) {
		Gem sourceGem = board.getGem(source.getMyX(), source.getMyY());
		board.performClick(sourceGem);
	}

	public void checkIfMatch(Gem a, Gem b) {
		if (gameTask2 != null)
			gameTask2.cancel(true);

		gameTask2 = gameExecutor2.submit(new Runnable() {
			public void run() {
				board.clearGrid(a, b);
				board.refillGrid();
			}
		});
	}

	public boolean getRemoveFlag(int x, int y) {
		return board.getRemoveFlag(x, y);
	}

	public void repaintBoard() {
		gameView.repaintBoard();
	}

	public void updatePoint(){
		gameView.updatePoint();
	}
}