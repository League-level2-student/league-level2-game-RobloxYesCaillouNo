import java.awt.Dimension;

import javax.swing.JFrame;

public class MainClass {
	JFrame window = new JFrame();

	final static int WIDTH = 2000;
	final static int HEIGHT = 1000;
	GamePanel gamePanel = new GamePanel();

	public static void main(String[] args) {
		MainClass game = new MainClass();
		game.setup();
	}

	void setup() {
		window.add(gamePanel);
		window.setVisible(true);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.addKeyListener(gamePanel);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.startGame();
	}

}
