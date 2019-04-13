import java.awt.Graphics;

public class Pickaxe extends Objects {

	int miningInProgress;

	public Pickaxe(int x, int y, int width, int height) {
		super(300, y, width, height);
		// TODO Auto-generated constructor stub
		miningInProgress = 100;

	}

	public void update() {
		super.update();

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.pickImg, x, y, 260, 260, null);
		System.out.println(x);
	}

}
