import java.awt.Graphics;
import java.util.ArrayList;

public class ObjManager {
	Miner miner;
	ArrayList<Crystal> crystallist = new ArrayList<Crystal>();
	ArrayList<bombs> bomblist = new ArrayList<bombs>();
	int score = 0;

	public int getScore() {
		return score;
	}

	public ObjManager(Miner miner) {
		// TODO Auto-generated constructor stub

	}

	public void update() {
		miner.update();

		for (int i = 0; i < crystallist.size(); i++) {
			crystallist.get(i).update();
		}

	}

	public void manageCrystalsAndBombs() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void addBomb(bombs bomb) {
		bomblist.add(bomb);
	}

	public void addCrystal(Crystal crystal) {
		crystallist.add(crystal);
	}

	public void checkCollision() {
		for (Crystal a : crystallist) {

			if (miner.collisionBox.intersects(a.collisionBox)) {

				score++;

			}

		}
		for (bombs b : bomblist) {
			if (miner.collisionBox.intersects(b.collisionBox)) {
				miner.isAlive = false;
			}
		}

	}

	public void purgeObjects() {
		for (int i = 0; i < crystallist.size(); i++) {
			if (crystallist.get(i).isAlive == false) {
				crystallist.remove(i);
			}

		}
		for (int i = 0; i < bomblist.size(); i++) {
			if (bomblist.get(i).isAlive == false) {
				bomblist.remove(i);

			}
		}
	}
}
