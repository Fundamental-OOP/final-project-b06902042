package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.GameController;

public class MenuView extends JPanel implements ActionListener {
	private JButton start;
	private final GameController gameController;

	public MenuView(GameController gameController) {
		super();
		this.gameController = gameController;
		setLayout(null);
		setBounds(0, 0, 880, 700);
		setBackground(new Color(88, 60, 100));

		start = new JButton();
		start.setBounds((getWidth() - 350) / 2, 350, 350, 300);
		start.setIcon(new ImageIcon("Images/play.png"));
		start.setBorder(null);
		start.addActionListener(this);
		start.setLayout(null);
		add(start);
	}

	public void paintComponent(Graphics g) {
		Image img = (new ImageIcon("Images/candy.jpg")).getImage();
		g.drawImage(img, 0, 0, 880, 700, 0, 0, img.getWidth(null), img.getHeight(null), null);
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
