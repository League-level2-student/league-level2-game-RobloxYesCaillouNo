import java.awt.Graphics;

public class Pickaxe extends Objects {

	int miningInProgress;

	public Pickaxe(int x, int y, int width, int height) {
		super(45, y, width, height);
		// TODO Auto-generated constructor stub
		miningInProgress = 20;

	}

	public void update() {
		super.update();

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pickImg, x, 500, 100, 100, null);
		// System.out.println(x);
	}

}
