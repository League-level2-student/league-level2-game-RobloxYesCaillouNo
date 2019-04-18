import java.awt.Graphics;

public class Pickaxe extends Objects {

	int miningInProgress;

	public Pickaxe(int x, int y, int width, int height) {
		super(40, y, width, height);
		// TODO Auto-generated constructor stub
		miningInProgress = 25;

	}

	public void update() {
		super.update();

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pickImg, x, 420, 75, 75, null);
		//System.out.println(x);
	}

}
