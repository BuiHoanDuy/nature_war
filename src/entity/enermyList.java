package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class enermyList {
	GamePanel gp;
	ArrayList<enermy> enerList;
	int map;

	public enermyList(GamePanel gp) {
		this.gp = gp;
		enerList = new ArrayList<enermy>();
	}

	public void map1() {
		enerList.add(new enermy(gp, 1008, 700 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1108, 800 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1208, 700 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1058, 800 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1028, 700 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1038, 800 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1308, 900 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1408, 200 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1508, 300 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1658, 400 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1728, 200 - gp.tileSize * 4, 20, 1));
		enerList.add(new enermy(gp, 1838, 100 - gp.tileSize * 4, 20, 1));
	}
	
	public void map2() {
		enerList.add(new enermy(gp));
	}
	
	public void roundSet() {
		switch (gp.map) {
		case 1:
			map1();
			break;
		}
	}

	public void update() {
		if (enerList.size() <= 0) {
			roundSet();
		}
		for (int i = 0; i < enerList.size(); i++) {
			enerList.get(i).update();
			if (enerList.get(i).hp <= 0) {
				enerList.remove(i);
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < enerList.size(); i++) {
			enerList.get(i).draw(g2);
		}
	}
}
