import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjManager {
	Miner miner;
	Pickaxe pickaxe;

	ArrayList<Crystal> crystallist = new ArrayList<Crystal>();
	ArrayList<Bombs> bomblist = new ArrayList<Bombs>();
	long itemTimer = 0;
	int itemSpawnTime = 300;
	int score = 0;

	public ObjManager(Miner miner, Pickaxe pickaxe) {
		// TODO Auto-generated constructor stub
		this.miner = miner;
		this.pickaxe = pickaxe;
	}

	public int getScore() {
		return score;
	}

	public void update() {
		miner.update();
		pickaxe.update();

		for (int i = 0; i < crystallist.size(); i++) {
			crystallist.get(i).update();
		}

		for (int i = 0; i < bomblist.size(); i++) {
			bomblist.get(i).update();
		}
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		miner.draw(g);
		pickaxe.draw(g);
		for (int i = 0; i < bomblist.size(); i++) {
			bomblist.get(i).draw(g);
		}
		for (int i = 0; i < crystallist.size(); i++) {
			crystallist.get(i).draw(g);
		}
	}

	public void addBomb(Bombs bomb) {
		bomblist.add(bomb);
	}

	public void addCrystal(Crystal crystal) {
		crystallist.add(crystal);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - itemTimer >= itemSpawnTime) {

			switch (new Random().nextInt(2)) {
			case 0:

				addBomb(new Bombs(new Random().nextInt(MainClass.WIDTH), 20, 100, 100));
				break;

			case 1:
				addCrystal(new Crystal(new Random().nextInt(MainClass.WIDTH), 20, 100, 100));
				break;
			}
			itemTimer = System.currentTimeMillis();
		}

	}

	public void checkCollision() {
		for (Crystal a : crystallist) {
			
			if (pickaxe.collisionBox.intersects(a.collisionBox)) {

				a.isAlive = false;

			}

			else if (a.y >= MainClass.HEIGHT) {

				a.isAlive = false;
			}
		}
		for (Bombs b : bomblist) {
			if (pickaxe.collisionBox.intersects(b.collisionBox)) {

				b.isAlive = false;
			} else if (b.y >= MainClass.HEIGHT) {
				b.isAlive = false;
			}

		}

		for (int i = 0; i < crystallist.size(); i++) {

			if (pickaxe.collisionBox.intersects(crystallist.get(i).collisionBox)) {
				crystallist.get(i).isAlive = false;
				score++;
			}
		}
		for (int i = 0; i < bomblist.size(); i++) {
			if (pickaxe.collisionBox.intersects(bomblist.get(i).collisionBox)) {
				bomblist.get(i).isAlive = false;
				pickaxe.isAlive = false;
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