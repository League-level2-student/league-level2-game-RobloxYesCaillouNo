import java.awt.Graphics;

public class Pickaxe extends Objects {

	public Pickaxe(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	public void update() {
		super.update();
	}
	public void draw(Graphics g) {
		g.drawImage(GamePanel.crystalImg, x, y, width, height, null);
	}
	
}