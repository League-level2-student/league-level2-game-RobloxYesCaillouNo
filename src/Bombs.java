
import java.awt.Graphics;

public class Bombs extends Objects
{

	public Bombs(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
public void update() {
	super.update();
	
}
public void draw(Graphics g) {
	g.drawImage(GamePanel.bombImg, x, y, width, height, null );
}

}
