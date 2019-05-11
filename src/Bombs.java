
import java.awt.Graphics;

public class Bombs extends Objects {
	int movement;

	public Bombs(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		movement = 7;
	}

	public void update() {
		super.update();
		if (x > 500) {
			direction = false;
		} else if (x < 0) {
			direction = true;
		}

		y = y + movement;

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.bombImg, x, y, width, height, null);
	}

}
