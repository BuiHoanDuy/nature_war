package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class archer_diagonal {
	public int x, y;
	int speed;
	BufferedImage archerImg;
	int index;
	public boolean completed; // use to check whether diagonalEffect is completed yet?
	ArrayList<BufferedImage> diagonalEffect;

	public archer_diagonal(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		index = 0;
		completed = false;
		this.speed = speed;
		diagonalEffect = new ArrayList<BufferedImage>();
		try {
			archerImg = ImageIO.read(getClass().getResourceAsStream("/res/diagonal_arrow/diagonal_arrow_.png"));

			for (int i = 1; i <= 8; i++) {
				String path = "/res/diagonal_arrow_hit_thorns/diagonal_arrow_hit_thorns_" + i + ".png";
				try {
					diagonalEffect.add(ImageIO.read(getClass().getResourceAsStream(path)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move() {
		x +=  speed-6;
		y +=  speed+2;
	}

	public void draw(Graphics2D g2) {
		if (index >= 8) {
			completed = true;
		} else {
			g2.drawImage(diagonalEffect.get(index), x, y, 200, 200, null);
			index++;
		}
	}
}
