import java.awt.Graphics;

public class Miner extends Objects{

	public Miner(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

public void update() {
	super.update();
}

public void draw(Graphics g) {
	g.drawImage(GamePanel.minerImg, x-60, y, 222, height, null);
}
	
}
