package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import main.GamePanel;

public class enermy {

	GamePanel gp;
	int x, y; // position
	public int hp;
	BufferedImage HPImg;
	ArrayList<BufferedImage> enermyImg = new ArrayList<BufferedImage>();
	BufferedImage bullet;
	int i = 0;
	boolean isIn = false;
	
	public enermy(GamePanel gp) {
		this.gp = gp;
		x = 1008;
		y = 675 - gp.tileSize * 4;
		hp = 10;
		getImg1();
	}

	public enermy(GamePanel gp, int x, int y, int hp, int type) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.hp = hp;
		if (type == 1) {
			getImg1();
		} else {
			getImg2();
		}
	}

	public void getImg1() {
		try {
			HPImg = ImageIO.read(getClass().getResourceAsStream("/res/red_HP.png"));
			bullet = ImageIO.read(getClass().getResourceAsStream("/res/enermy1/bullet1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i <= 4; i++) {
			String path = "/res/enermy1/enermy1_" + i + ".png";
			try {
				enermyImg.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void getImg2() {
		try {
			HPImg = ImageIO.read(getClass().getResourceAsStream("/res/red_HP.png"));
			bullet = ImageIO.read(getClass().getResourceAsStream("/res/enermy2/bullet2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i <= 4; i++) {
			String path = "/res/enermy1/enermy2_" + i + ".png";
			try {
				enermyImg.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void draw(Graphics2D g2) {
		if (i + 1 >= enermyImg.size()) {
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(enermyImg.get(i), x, y, gp.tileSize, gp.tileSize, null);
		g2.drawImage(HPImg, x, y - gp.tileSize + 30, hp * 3, 20, null);
		int random = ThreadLocalRandom.current().nextInt(1, 20);
		if (random == 1) {
			g2.drawImage(bullet, x-60, y+5, 60, 10, null);
		}
		if (random == 1 && isIn && !gp.isDefending) {
			gp.HP_Left -= 1;
			gp.isBeingHit = true;
		} else if (random == 1 && isIn && gp.isDefending) {
			gp.isBeingHitButDefend = true;
		}
	}

	public void update() {
		move();
		if (gp.isHitting && isIn) { // monster is being hit
			x +=3;
			hp -= 1;
		}
	}

	public void move() {
		if (x > gp.playerX) {
			x --;
		}
		if (x < gp.playerX) {
			x ++;
		}
		if (y < gp.playerY) {
			y ++;
		}
		if (y > gp.playerY) {
			y --;
		}
		
		if (x < gp.playerX + 60 && x > gp.playerX - 92 && y > gp.playerY - 40 && y < gp.playerY  + 50) {
			isIn = true;
		} else isIn = false;
	}
}
