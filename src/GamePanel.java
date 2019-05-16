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
	Miner miner = new Miner(0, 500, 91, 111);
	Pickaxe pick = new Pickaxe(45, 500, 100, 100);

	long startTime = System.currentTimeMillis();
	long timeElapsed = 0;
	long timeLeft = 85;

	ObjManager objectmanager = new ObjManager(miner, pick);
	public static BufferedImage minerImg;
	public static BufferedImage bombImg;
	public static BufferedImage bgImg;
	public static BufferedImage crystalImg;
	public static BufferedImage pickImg;
	public static BufferedImage winImg;
	public static BufferedImage deathImg;
	Font titleFont = new Font("Roboto", Font.BOLD, 48);
	Font retryFont = new Font("Comic Sans", Font.ITALIC, 38);
	Font timerFont = new Font("Roboto", Font.PLAIN, 12);
	Font pointsOrLives = new Font("Verdana", Font.PLAIN, 12);
	Font winFont = new Font("Comic Sans", Font.BOLD, 50);
	

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;

	String l1 = "The objective is simple: Press space to mine. ";
	String l2 = "Do not mine a single bomb. Mine at least 25 crystals.";
	String l3 = "Use arrow keys to move pickaxe around";
	String t3xt = l1 + "\n" + l2 + "\n" + l3;
	int score;
	int currentState = MENU_STATE;

	public GamePanel() {
		try {
			minerImg = ImageIO.read(this.getClass().getResourceAsStream("MinerGuy.jpg"));
			bombImg = ImageIO.read(this.getClass().getResourceAsStream("RblxBomb.jpg"));
			crystalImg = ImageIO.read(this.getClass().getResourceAsStream("RblxCrystal.jpg"));
			bgImg = ImageIO.read(this.getClass().getResourceAsStream("bgImage.jpg"));
			pickImg = ImageIO.read(this.getClass().getResourceAsStream("pickaxe.png"));
			winImg = ImageIO.read(this.getClass().getResourceAsStream("StockPHOTOYouwin.jpg"));
			deathImg = ImageIO.read(this.getClass().getResourceAsStream("DeathImg.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void startGame() {
		// TODO Auto-generated method stub
		timer.start();

	}

	public void timeRemaining() {
		timeElapsed = (System.currentTimeMillis() - startTime) / 1000;
		timeLeft = 85 - timeElapsed;

	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		objectmanager.update();
		timeRemaining();
		
		objectmanager.manageEnemies();
		objectmanager.checkCollision();
		objectmanager.purgeObjects();
		if (miner.isAlive == false || pick.isAlive == false) {
			currentState = END_STATE;
		}
		if (objectmanager.getScore() >= 25) {
			currentState++;
		}
		if (timeLeft == 0) {
			currentState++;
		}

	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, MainClass.WIDTH, MainClass.HEIGHT);
		g.setColor(Color.red);
		g.setFont(titleFont);
		g.drawString("Press enter to start!", 380, 360);
		g.setColor(Color.DARK_GRAY);
		g.setFont(titleFont);
		g.drawString("Game: Mining Madness!", 404, 111);
		g.setColor(Color.GREEN);
		g.setFont(titleFont);
		g.drawString("Left Shift for instructions/rules", 380, 480);

	}

	public void drawGameState(Graphics g) {
		timeRemaining();
		g.drawImage(bgImg, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
		g.setColor(Color.WHITE);
		g.setFont(timerFont);
		g.drawString("Time left: " + timeLeft, 40, 80);
		g.setColor(Color.WHITE);
		g.setFont(pointsOrLives);
		g.drawString("Points: " + objectmanager.getScore(), 40, 50);
		g.setColor(Color.WHITE);
		g.drawString("Lives: " + objectmanager.lives, 40, 111);
		
		objectmanager.draw(g);
	}

	public void drawEndState(Graphics g) {

		if (timeLeft <= 0 || objectmanager.getScore() < 25) {
			g.drawImage(deathImg, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
			
			g.setColor(Color.RED);
			g.setFont(retryFont);
			g.drawString("You mined " + objectmanager.getScore() + "/25 crystals! Try again :[", 80, 110);

		} else if (objectmanager.getScore() >= 25) {
			g.drawImage(winImg, 0, 0, MainClass.WIDTH, MainClass.HEIGHT, null);
			g.setColor(Color.CYAN);
			g.setFont(winFont);
			g.drawString("Great Job!! You won! :DDD", 80, 110);

		}
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 333, 444);

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

			// currentState++;
			if (currentState >= END_STATE) {
				currentState = MENU_STATE;
				miner = new Miner(0, 500, 91, 111);
				pick = new Pickaxe(45, 500, 100, 100);
				objectmanager = new ObjManager(miner, pick);
				startTime = System.currentTimeMillis();

			}

			else if (currentState == MENU_STATE) {

				currentState++;

			}
		}
		if (currentState == GAME_STATE) {

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