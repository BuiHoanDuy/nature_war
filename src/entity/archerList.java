package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class archerList {
	ArrayList<archer> archerList ;
	GamePanel gp;
	public archerList(GamePanel gp) {
		archerList = new ArrayList<archer>();
		this.gp = gp;
	}
	public void draw(Graphics2D g2) {
		for (int i=0; i<archerList.size();i++) {
			g2.drawImage(archerList.get(i).archerImg, archerList.get(i).x, archerList.get(i).y, 200, 60, null);
		}
	}
	public void update() {
		for (int i=0; i<archerList.size();i++) {
			if (archerList.get(i).x >= gp.screenWidth) {
				archerList.remove(i);
			} else archerList.get(i).move();  
		}
	}
}
