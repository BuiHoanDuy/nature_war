package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class archerList_diagonal {
	ArrayList<archer_diagonal> archerDiagonalList;
	GamePanel gp;

	public archerList_diagonal(GamePanel gp) {
		archerDiagonalList = new ArrayList<archer_diagonal>();
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < archerDiagonalList.size(); i++) {
			if (archerDiagonalList.get(i).y >= 550) {
				archerDiagonalList.get(i).draw(g2);
			} else {
				archerDiagonalList.get(i).move();
				g2.drawImage(archerDiagonalList.get(i).archerImg, archerDiagonalList.get(i).x , archerDiagonalList.get(i).y,
					200, 200, null);
			}

		}

	}

	public void update() {
		for (int i = 0; i < archerDiagonalList.size(); i++) {		
			if (archerDiagonalList.get(i).completed) {
				archerDiagonalList.remove(i);
				System.out.println("Yessss");
			}
		}
	}
}
