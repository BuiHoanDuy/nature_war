package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.keyHandler;
import controller.mouseController;
import entity.enermy;
import entity.enermyList;
import entity.fire;
import entity.leaf;
import entity.water;
import entity.wind;

public class GamePanel extends JPanel implements Runnable {
	// screen setting
	final int originalTileSize = 16; // 16x16
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 21;
	final int maxScreenRow = 15;

	public final int screenWidth = tileSize * maxScreenCol; // 1008px
	public final int screenHeight = tileSize * maxScreenRow; // 720px

	int fps = 60;

	public int countToDraw = 0; // count to draw next action;
	public int countToDrawIdle = 0;

	Thread gameThread; // fps
	keyHandler keyH = new keyHandler(this);
	mouseController Mouse = new mouseController(this);
	Sound sound = new Sound();

	// Initialize some objects
	water waterWarrior = new water(this, keyH, 1);
	wind windWarrior = new wind(this, keyH, 1);
	leaf leafWarrior = new leaf(this, keyH, 1);
	fire fireWarrior = new fire(this, keyH, 1);

	public int warriorChoice = 1; // 1: wind, 2: fire, 3: leaf, 4: water

	public int playerX, playerY;
	public boolean isHitting = false;
	public boolean isDefending = false;
	public boolean isBeingHit = false;
	public boolean isBeingHitButDefend = false;

	public int map = 1;
	public int HP_Left = 20; // hp of warrior
	public int jTime, kTime, lTime, uTime, iTime, oTime; // time to reset a skill

	enermyList enerList = new enermyList(this);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);

		this.addMouseListener(Mouse);
		this.addMouseMotionListener(Mouse);
		this.addKeyListener(keyH);
		this.setFocusable(true);

		playMusic(0);
		setValueTimer();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		// --------- set FPS ------------
		double drawInterval = 1000000000 / fps; // 0,01666seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {
			// update:
			update();

			// draw:
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// -----------------------------

	}

	public void update() {
		// Put Update function here
		//waterWarrior.update();
		windWarrior.update();
		//leafWarrior.update();
		//fireWarrior.update();

		updateTimer();
		enerList.update();
	}

	public void paintComponent(Graphics g) {
		// Put Draw Function here
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		drawBackground(g2);
		drawInformation(g2);
		//waterWarrior.draw(g2);
		 windWarrior.draw(g2);
		// leafWarrior.draw(g2);
		// fireWarrior.draw(g2);

		enerList.draw(g2);

		g2.dispose();
		try {
			Thread.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playMusic(int i) {
//		sound.setFile(i);
//		sound.play();
//		sound.loop();
	}

	public void stopMusic() {
//		sound.stop();
	}

	public void playSE(int i) { // sound effect
//		sound.setFile(i);
//		sound.play();
	}

	public void drawBackground(Graphics2D g2) {
		BufferedImage background = null;
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/res/Map_Hill.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
	}

	public void drawInformation(Graphics2D g2) {
		BufferedImage u = null;
		BufferedImage i = null;
		BufferedImage o = null;
		BufferedImage j = null;
		BufferedImage k = null;
		BufferedImage l = null;
		BufferedImage character = null;
		BufferedImage HP = null; // HP of entities
		BufferedImage timer = null;
		try {
			j = ImageIO.read(getClass().getResourceAsStream("/res/vibes/1.png"));
			k = ImageIO.read(getClass().getResourceAsStream("/res/vibes/2.png"));
			l = ImageIO.read(getClass().getResourceAsStream("/res/vibes/3.png"));
			u = ImageIO.read(getClass().getResourceAsStream("/res/vibes/4.png"));
			i = ImageIO.read(getClass().getResourceAsStream("/res/vibes/5.png"));
			o = ImageIO.read(getClass().getResourceAsStream("/res/vibes/6.png"));
			HP = ImageIO.read(getClass().getResourceAsStream("/res/green_HP.png"));
			timer = ImageIO.read(getClass().getResourceAsStream("/res/BHP_yellow.png"));
			if (warriorChoice == 1) {
				character = ImageIO.read(getClass().getResourceAsStream("/res/vibes/wind.png"));
			} else if (warriorChoice == 2) {
				character = ImageIO.read(getClass().getResourceAsStream("/res/vibes/fire.png"));
			} else if (warriorChoice == 3) {
				character = ImageIO.read(getClass().getResourceAsStream("/res/vibes/leaf.png"));
			} else {
				character = ImageIO.read(getClass().getResourceAsStream("/res/vibes/water.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (HP_Left >= 0)
			g2.drawImage(HP, 65, tileSize * 3, HP_Left * 7, 20, null);
		g2.drawImage(character, 63, 0, tileSize * 3, tileSize * 3, null);
		g2.drawImage(j, 252, 0, tileSize * 2, tileSize * 2, null);
		g2.drawImage(k, 378, 0, tileSize * 2, tileSize * 2, null);
		g2.drawImage(l, 504, 0, tileSize * 2, tileSize * 2, null);
		g2.drawImage(u, 630, 0, tileSize * 2, tileSize * 2, null);
		g2.drawImage(i, 756, 0, tileSize * 2, tileSize * 2, null);
		g2.drawImage(o, 882, 0, tileSize * 2, tileSize * 2, null);

		g2.drawImage(timer, 252, tileSize * 2, 12*jTime, 20, null);
		g2.drawImage(timer, 378, tileSize * 2, 3*(kTime/10), 20, null);
		g2.drawImage(timer, 504, tileSize * 2, 2*(lTime/10), 20, null);
		g2.drawImage(timer, 630, tileSize * 2, 2*(uTime/10), 20, null);
		g2.drawImage(timer, 756, tileSize * 2, 9*(iTime/100), 20, null);
		g2.drawImage(timer, 882, tileSize * 2, 9*(oTime/10), 20, null);
	}

	public void checkHiting() {
		if (keyH.OKey) {
			isDefending = true;
		}
	}

	public void setValueTimer() {
		jTime = 10;
		kTime = 300;
		lTime = 400;
		uTime = 400;
		iTime = 1000;
		oTime = 100;
	}

	public void updateTimer() {
		if (jTime > 0)
			jTime--;
		if (kTime > 0)
			kTime--;
		if (lTime > 0)
			lTime--;
		if (uTime > 0)
			uTime--;
		if (iTime > 0)
			iTime--;
		if (oTime > 0)
			oTime--;
	}
}