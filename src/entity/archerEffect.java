package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class archerEffect {
	ArrayList<BufferedImage> effect = new ArrayList<BufferedImage>();
	GamePanel gp;
	public int i;
	int x, y;
	public archerEffect(GamePanel gp, int x, int y) {
		this.gp = gp;
		getImage();
		this.x = x; this.y = y;
		i = 0;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(effect.get(i),x, 675 - gp.tileSize * 4, 400, 200, null);
		i++;
	}
	
	public void getImage() {
		for (int i = 1; i <= 18; i++) {
			String path = "/res/PNG_Leaf/projectiles_and_effects/arrow_shower_effect/arrow_shower_effect_" + i + ".png";
			try {
				effect.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
