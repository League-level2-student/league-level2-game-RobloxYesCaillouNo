import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	Timer timer = new Timer(1000 / 60, this);
	Miner miner = new Miner(980, 700, 100, 250);
	Pickaxe pick = new Pickaxe(1024, 700, 50, 50);
	int timeLeft = 45;

	ObjManager objectmanager = new ObjManager(miner, pick);
	public static BufferedImage minerImg;
	public static BufferedImage bombImg;
	public static BufferedImage bgImg;
	public static BufferedImage crystalImg;
	public static BufferedImage pickImg;
	Font titleFont = new Font("Roboto", Font.BOLD, 48);
	Font retryFont = new Font("Arial", Font.ITALIC, 28);
	Font timerFont = new Font("Roboto", Font.PLAIN, 12);
	Font points = new Font("Verdana", Font.PLAIN, 12);
	Font winFont = new Font("Comic Sans", Font.ITALIC, 28);
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;

	String l1 = "Press space to mine. Do not mine a single bomb. Mine at least 25 crystals. ";
	String l2 = "Use arrow keys to move pickaxe around";
	String t3xt = l1 + "\n" + l2;
	int score;
	int currentState = MENU_STATE;

	public GamePanel() {
		try {
			minerImg = ImageIO.read(this.getClass().getResourceAsStream("MinerGuy.jpg"));
			bombImg = ImageIO.read(this.getClass().getResourceAsStream("RblxBomb.jpg"));
			crystalImg = ImageIO.read(this.getClass().getResourceAsStream("RblxCrystal.jpg"));
			bgImg = ImageIO.read(this.getClass().getResourceAsStream("bgImage.jpg"));
			pickImg = ImageIO.read(this.getClass().getResourceAsStream("pickaxe.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void startGame() {
		// TODO Auto-generated method stub
		timer.start();

	}

	public void time() throws InterruptedException {
		for (int i = 0; i < timeLeft; i++) {
			wait(1000);
			timeLeft = timeLeft - 1;
		}

	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		objectmanager.update();
		objectmanager.manageEnemies();
		objectmanager.checkCollision();
		objectmanager.purgeObjects();
		if (miner.isAlive == false && pick.isAlive == false) {
			currentState = END_STATE;
		}
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 2000, 1000);
		g.setColor(Color.red);
		g.setFont(titleFont);
		g.drawString("Press enter to start!", 380, 360);
		g.setColor(Color.DARK_GRAY);
		g.setFont(titleFont);
		g.drawString("Game: Crystals n' Bombs", 404, 111);
		g.setColor(Color.GREEN);
		g.setFont(titleFont);
		g.drawString("LShift for instructions/rules", 380, 480);

	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(timerFont);
		g.drawString("Time left: " + timeLeft, 150, 250);
		g.setColor(Color.BLACK);
		g.setFont(points);
		g.drawString("Points: " + objectmanager.getScore(), 420, 250);

		g.drawImage(bgImg, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
		objectmanager.draw(g);
	}

	public void drawEndState(Graphics g) {

		if (timeLeft == 0 || objectmanager.getScore() < 25) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.YELLOW);
			g.setFont(retryFont);
			g.drawString("You mined " + objectmanager.getScore() + "/25 crystals! Try again :[", 999, 110);

		} else if (objectmanager.getScore() >= 25) {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.yellow);
			g.setFont(winFont);
			g.drawString("Great Job!! You won! :DDD", 999, 110);

		}
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to restart", 222, 444);

	}

	public void drawWinState(Graphics g) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			currentState++;
			if (currentState >= END_STATE) {
				currentState = MENU_STATE;
				miner = new Miner(980, 700, 100, 250);
				pick = new Pickaxe(1060, 700, 50, 50);
				objectmanager = new ObjManager(miner, pick);
			}

			if (currentState == MENU_STATE) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					currentState = currentState + 1;
				}
			}
		}
		if (currentState == GAME_STATE) {

			if (timeLeft == 0) {
				currentState = currentState + 1;
			}
			if (e.getKeyCode() == 37) {
				pick.x -= pick.miningInProgress;

			} else if (e.getKeyCode() == 39) {
				pick.x += pick.miningInProgress;
			}
		
		
		}

		if (currentState == MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				JOptionPane.showMessageDialog(null, t3xt);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
