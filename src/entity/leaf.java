package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.keyHandler;
import main.GamePanel;

public class leaf extends Entity{
	GamePanel gp;
	keyHandler KeyH;
	archerList archerList;
	archerEffectList archerEffectList;
	archerList_diagonal archerDiagonal;
	int i = 0;

	public leaf(GamePanel gp, keyHandler keyH, int colorHP) { // colorHP: 1:green; 2:red;
		super();
		this.gp = gp;
		KeyH = keyH;
		archerList = new archerList(gp);
		archerEffectList = new archerEffectList(gp);
		archerDiagonal = new archerList_diagonal(gp);
		
		setDefaultValue(colorHP);
		getImage();

		speed = 8;
		x = 200;
		y = 675 - gp.tileSize * 4;
		HP_Left = 20;
	}

	public void setDefaultValue(int colorHP) {
		try {
			if (colorHP == 1)
				HP = ImageIO.read(getClass().getResourceAsStream("/res/green_HP.png"));
			else
				HP = ImageIO.read(getClass().getResourceAsStream("/res/red_HP.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		archerList.update();
		archerEffectList.update();
		archerDiagonal.update();
	}
	
	public void draw(Graphics2D g2) {
		archerList.draw(g2);
		archerEffectList.draw(g2);
		archerDiagonal.draw(g2);
		g2.drawImage(HP, x + 170, y + gp.tileSize * 4, HP_Left * 3, 20, null);
		
		// drawIdle(g2);

		if (HP_Left <= 0) {
			drawDeath(g2);
		} else {

			if (KeyH.upPressed) {
				y -= speed;
				if (!KeyH.upRight && !KeyH.downRight && !KeyH.upLeft && !KeyH.downLeft)
					drawJumpUp(g2);
			}
			if (KeyH.downPressed) {
				y += speed;
				if (!KeyH.upRight && !KeyH.downRight && !KeyH.upLeft && !KeyH.downLeft)
					drawJumpDown(g2);
			}
			if (KeyH.leftPressed) {
				x -= speed;
				if (!KeyH.upRight && !KeyH.downRight && !KeyH.upLeft && !KeyH.downLeft)
					drawRun(g2);
			}
			if (KeyH.rightPressed) {
				x += speed;
				if (!KeyH.upRight && !KeyH.downRight && !KeyH.upLeft && !KeyH.downLeft)
					drawRun(g2);
			}
			if (KeyH.upLeft) {
				x -= (int) Math.sqrt(speed * speed - y * y);
				y -= (int) Math.sqrt(speed * speed - x * x);
				drawJumpUp(g2);
			}
			if (KeyH.upRight) {
				x += (int) Math.sqrt(speed * speed - y * y);
				y -= (int) Math.sqrt(speed * speed - x * x);
				drawJumpUp(g2);
			}
			if (KeyH.downLeft) {
				x -= (int) Math.sqrt(speed * speed - y * y);
				y += (int) Math.sqrt(speed * speed - x * x);
				drawJumpDown(g2);
			}
			if (KeyH.downRight) {
				x += (int) Math.sqrt(speed * speed - y * y);
				y += (int) Math.sqrt(speed * speed - x * x);
				drawJumpDown(g2);
			}
			if (!KeyH.upLeft && !KeyH.upRight && !KeyH.downLeft && !KeyH.downRight && !KeyH.upPressed
					&& !KeyH.downPressed && !KeyH.leftPressed && !KeyH.rightPressed && !KeyH.JKey && !KeyH.KKey
					&& !KeyH.LKey && !KeyH.UKey && !KeyH.IKey && !KeyH.OKey && !KeyH.HKey) {
				drawIdle(g2);
			}
		}

		// skills:
		if (KeyH.JKey == true) {
			drawATK1(g2);
		} else if (KeyH.KKey == true) {
			drawATK2(g2);
			if (i == 7)
			archerList.archerList.addLast(new archer(x+50, y+110, 8));
		} else if (KeyH.LKey == true) {
			drawATK3(g2);
			if (i == atk3.size()-1) {
				archerEffectList.archerEffectList.add(new archerEffect(gp, x+400, y));
			}
		} else if (KeyH.UKey == true) {
			drawAirATK(g2);
			if (i == air_atk.size()-1) {
				archerDiagonal.archerDiagonalList.add(new archer_diagonal(x+100, y+50, 20));
			}
		} else if (KeyH.IKey == true) {
			drawSpATK(g2);
		} else if (KeyH.OKey == true) {
			drawDefend(g2);
		}
	}

	public void drawIdle(Graphics2D g2) {
		if (idle.size() <= i + 1) {
			i = 0;
		} else
			i++;
		g2.drawImage(idle.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawATK1(Graphics2D g2) {
		if (i+1 >= atk1.size()) {
			KeyH.JKey = false;
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(atk1.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawATK2(Graphics2D g2) {
		if (i + 1 >= atk2.size()) {
			KeyH.KKey = false;
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(atk2.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawATK3(Graphics2D g2) {
		if (i + 1 >= atk3.size()) {
			KeyH.LKey = false;
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(atk3.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawAirATK(Graphics2D g2) {
		if (i + 1 >= air_atk.size()) {
			KeyH.UKey = false;
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(air_atk.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawSpATK(Graphics2D g2) {
		if (i + 1 >= sp_atk.size()) {
			KeyH.IKey = false;
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(sp_atk.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawDefend(Graphics2D g2) {
		if (i + 1 >= defend.size()) {
			KeyH.OKey = false;
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(defend.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawRun(Graphics2D g2) {
		if (i + 1 >= run.size()) {
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(run.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawJumpUp(Graphics2D g2) {
		if (i + 1 >= jumpUp.size()) {
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(jumpUp.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawJumpDown(Graphics2D g2) {
		if (i + 1 >= jumpDown.size()) {
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(jumpDown.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawDeath(Graphics2D g2) {
		if (i + 1 >= death.size()) {
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(death.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void drawTakeHit(Graphics2D g2) {
		if (i + 1 >= takeHit.size()) {
			i = 0;
		} else {
			i++;
		}
		g2.drawImage(takeHit.get(i), x, y, gp.tileSize * 8, gp.tileSize * 4, null);
	}

	public void getImage() {

		for (int i = 1; i <= 10; i++) {
			String path = "/res/PNG_Leaf/1_atk/1_atk_" + i + ".png";
			try {
				atk1.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 15; i++) {
			String path = "/res/PNG_Leaf/2_atk/2_atk_" + i + ".png";
			try {
				atk2.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 12; i++) {
			String path = "/res/PNG_Leaf/3_atk/3_atk_" + i + ".png";
			try {
				atk3.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 10; i++) {
			String path = "/res/PNG_Leaf/air_atk/air_atk_" + i + ".png";
			try {
				air_atk.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 17; i++) {
			String path = "/res/PNG_Leaf/sp_atk/sp_atk_" + i + ".png";
			try {
				sp_atk.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 19; i++) {
			String path = "/res/PNG_Leaf/defend/defend_" + i + ".png";
			try {
				defend.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 3; i++) {
			String path = "/res/PNG_Leaf/jump_up/jump_up_" + i + ".png";
			try {
				jumpUp.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 3; i++) {
			String path = "/res/PNG_Leaf/jump_down/jump_down_" + i + ".png";
			try {
				jumpDown.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 10; i++) {
			String path = "/res/PNG_Leaf/run/run_" + i + ".png";
			try {
				run.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 19; i++) {
			String path = "/res/PNG_Leaf/death/death_" + i + ".png";
			try {
				death.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 6; i++) {
			String path = "/res/PNG_Leaf/take_hit/take_hit_" + i + ".png";
			try {
				takeHit.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 12; i++) {
			String path = "/res/PNG_Leaf/idle/idle_" + i + ".png";
			try {
				idle.add(ImageIO.read(getClass().getResourceAsStream(path)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}