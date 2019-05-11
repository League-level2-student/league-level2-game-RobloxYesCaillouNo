
import java.awt.Graphics;

public class Crystal extends Objects {
	int moving;
	boolean direction = true;

	public Crystal(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		moving = 6;
	}

	public void update() {
		super.update();
		if (x > 500) {
			direction = false;
		} else if (x < 0) {
			direction = true;
		}

		y = y + moving;

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.crystalImg, x, y, width, height, null);
	}
}
