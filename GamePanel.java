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

public class GamePanel extends JPanel implements ActionListener, KeyListener{
		
		Timer timer = new Timer(1000 / 60, this);
		Miner miner = new Miner(980, 700, 100, 250);
		int timeLeft = 45;
		ObjManager objectmanager = new ObjManager(miner);
		public static BufferedImage minerImg;
		public static BufferedImage bombImg;
		public static BufferedImage bgImg;
		public static BufferedImage menustateImg;
		public static BufferedImage crystalImg;
		public static BufferedImage pickImg;
		Font titleFont = new Font("Roboto", Font.BOLD, 48);
		Font retryFont = new Font("Arial", Font.ITALIC, 28);
		Font timerFont = new Font("Roboto", Font.PLAIN, 12);
		final int MENU_STATE = 0;
		final int GAME_STATE = 1;
		final int END_STATE = 2;
		int score;
		int currentState = MENU_STATE;
		public GamePanel() {
			try {
				minerImg = ImageIO.read(this.getClass().getResourceAsStream("MinerGuy.jpg"));
				bombImg = ImageIO.read(this.getClass().getResourceAsStream("RblxBomb.jpg"));
				crystalImg = ImageIO.read(this.getClass().getResourceAsStream("RblxCrystal.jpg"));
				bgImg = ImageIO.read(this.getClass().getResourceAsStream("background.jpg"));
				menustateImg = ImageIO.read(this.getClass().getResourceAsStream("start.jpg"));

				
				
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void startGame() {
		// TODO Auto-generated method stub
		timer.start();
	
	}
		
		public void updateMenuState() {

		}

		public void updateGameState() {
			objectmanager.update();
			objectmanager.manageCrystalsAndBombs();
			objectmanager.checkCollision();
			objectmanager.purgeObjects();
			if (miner.isAlive == false) {
				currentState = END_STATE;
			}
		}

		public void updateEndState() {

		}

		public void drawMenuState(Graphics g) {
		
		}

		public void drawGameState(Graphics g) {
			objectmanager.draw(g);
			g.setFont(timerFont);
			g.drawString("Time left: " + timeLeft , 700, 300);
		}

		public void drawEndState(Graphics g) {
			
			g.drawString("You mined " + objectmanager.getScore() + " crystals!", 999, 50);

			g.setFont(retryFont);
			g.drawString("Press ENTER to restart", 1099, 888);

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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {

			currentState++;
			if (currentState >= END_STATE) {
				currentState = MENU_STATE;
				miner = new Miner(980, 700, 100, 250);
				objectmanager = new ObjManager(miner);
			}

			 if (currentState == MENU_STATE) {
				if (e.getKeyCode() == 32) {
					JOptionPane.showMessageDialog(null, "Press SPACE to mine. /n Do not mine a single bomb. Constantly mine crystals. Survive till the end...");
				}

			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
