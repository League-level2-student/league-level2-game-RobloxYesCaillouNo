import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ObjManager {
	Miner miner;
	Pickaxe pickaxe;
	Date date = new Date();
	ArrayList<Crystal> crystallist = new ArrayList<Crystal>();
	ArrayList<Bombs> bomblist = new ArrayList<Bombs>();
	long itemTimer = 0;
	int itemSpawnTime = 1500;
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

				addBomb(new Bombs(250, 20, 50, 50));
				break;

			case 1:
				addCrystal(new Crystal(250, 20, 50, 50));
				break;
			}
			itemTimer = System.currentTimeMillis();
		}

	}

	public void checkCollision() {
		for (Crystal a : crystallist) {

			if (pickaxe.collisionBox.intersects(a.collisionBox)) {
				
				score = score + 1;
				a.isAlive = false;
				
			}
			
			else if(a.y >= MainClass.HEIGHT) {
			
				a.isAlive = false;
			}
		}
		for (Bombs b : bomblist) {
			if (pickaxe.collisionBox.intersects(b.collisionBox)) {
				miner.isAlive = false;
				pickaxe.isAlive = false;
				b.isAlive = false;
			}
			else if(b.y >= MainClass.HEIGHT) {
				b.isAlive = false;
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
