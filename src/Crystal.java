
import java.awt.Graphics;

public class Crystal extends Objects {
	int moving;

	public Crystal(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	moving = 85;
	}

	public void update() {
		super.update();
		y = y + moving;

		if (y == 900) {
			moving = 0;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.crystalImg, x, y, width, height, null);
	}
}
