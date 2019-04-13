
import java.awt.Graphics;

public class Bombs extends Objects {
	int place;
	int movement;
	public Bombs(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		movement = 85;
	}

	public void update() {
		super.update();
		y = y + movement;
		
		if (y == 900) {
			movement = 0;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.bombImg, x, y, width, height, null);
	}

}
