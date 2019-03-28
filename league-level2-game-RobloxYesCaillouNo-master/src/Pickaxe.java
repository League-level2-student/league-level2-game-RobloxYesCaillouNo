import java.awt.Graphics;

public class Pickaxe extends Objects {

	int miningInProgress;
	
	public Pickaxe(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		miningInProgress = 20;
		
	}
	public void update() {
		super.update();
	}
	public void draw(Graphics g) {
		g.drawImage(GamePanel.pickImg, x , y, 160, 160, null);
	}
	
}
