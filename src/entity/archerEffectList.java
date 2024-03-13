package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;

public class archerEffectList {
	ArrayList<archerEffect> archerEffectList = new ArrayList<archerEffect>();
	GamePanel gp;
	
	public archerEffectList(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		for (int i=0; i<archerEffectList.size();i++) {
			archerEffectList.get(i).draw(g2);
		}
	}
	public void update() {
		for (int i=0; i<archerEffectList.size();i++) {
			if (archerEffectList.get(i).i >= archerEffectList.get(i).effect.size()-1) {
				archerEffectList.remove(i);
			}
		}
	}
}
