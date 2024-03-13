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
	
	//Initialize some objects
	water waterWarrior = new water(this, keyH, 1);
	wind windWarrior = new wind(this, keyH, 2);
	leaf leafWarrior = new leaf(this, keyH, 1);
	fire fireWarrior = new fire(this, keyH, 2);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);

		this.addMouseListener(Mouse);
		this.addMouseMotionListener(Mouse);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		playMusic(0);
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
		//Put Update function here
		waterWarrior.update();
		windWarrior.update();
		leafWarrior.update();
		fireWarrior.update();
	}
	
	public void paintComponent(Graphics g) {
		// Put Draw Function here
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		drawBackground(g2);
		
		//waterWarrior.draw(g2);
		//windWarrior.draw(g2);
		leafWarrior.draw(g2);
		//fireWarrior.draw(g2);
		
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
}