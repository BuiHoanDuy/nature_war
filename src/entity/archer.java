package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class archer {
	public int x, y;
	int speed;
	BufferedImage archerImg;
	public archer(int x, int y, int speed) {
		this.x = x; this.y = y; this.speed = speed;
		try {
			archerImg = ImageIO.read(getClass().getResourceAsStream("/res/PNG_Leaf/projectiles_and_effects/arrow_hit_poison_4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void move() {
		x+=speed;
	}
}
