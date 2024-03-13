package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class keyHandler implements KeyListener {

	GamePanel gp;

	public keyHandler(GamePanel gp) {
		super();
		this.gp = gp;
	}

	public String direction;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean upLeft, upRight, downLeft, downRight;
	public boolean JKey, KKey, LKey, UKey, IKey, OKey, HKey;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
			direction = "left";
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
			direction = "right";
		}
		if (code == KeyEvent.VK_J) {
			JKey = true;
		}
		if (code == KeyEvent.VK_K) {
			KKey = true;
		}
		if (code == KeyEvent.VK_L) {
			LKey = true;
		}
		if (code == KeyEvent.VK_U) {
			UKey = true;
		}
		if (code == KeyEvent.VK_I) {
			IKey = true;
		}
		if (code == KeyEvent.VK_O) {
			OKey = true;
		}
		if (code == KeyEvent.VK_H) {
			HKey = true;
		}
		if (upPressed && leftPressed) {
			upLeft = true;
		}
		if (upPressed && rightPressed) {
			upRight = true;
		}
		if (downPressed && leftPressed) {
			downLeft = true;
		}
		if (downPressed && rightPressed) {
			downRight = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
			direction = "left";
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
			direction = "right";
		}
		if (code == KeyEvent.VK_J) {
			JKey = true;
		}
		if (code == KeyEvent.VK_K) {
			KKey = true;
		}
		if (code == KeyEvent.VK_L) {
			LKey = true;
		}
		if (code == KeyEvent.VK_U) {
			UKey = true;
		}
		if (code == KeyEvent.VK_I) {
			IKey = true;
		}
		if (code == KeyEvent.VK_O) {
			OKey = true;
		}
		if (code == KeyEvent.VK_H) {
			HKey = true;
		}
		if (upPressed && leftPressed) {
			upLeft = true;
		}
		if (upPressed && rightPressed) {
			upRight = true;
		}
		if (downPressed && leftPressed) {
			downLeft = true;
		}
		if (downPressed && rightPressed) {
			downRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gp.countToDraw = 0;
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
			upLeft = false;
			upRight = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
			downLeft = false;
			downRight = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
			upLeft = false;
			downLeft = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
			upRight = false;
			downRight = false;
		}
//		if (code == KeyEvent.VK_J) {
//			JKey = false;
//		}
//		if (code == KeyEvent.VK_K) {
//			KKey = false;
//		}
//		if (code == KeyEvent.VK_L) {
//			LKey = false;
//		}
//		if (code == KeyEvent.VK_U) {
//			UKey = false;
//		}
//		if (code == KeyEvent.VK_I) {
//			IKey = false;
//		}
//		if (code == KeyEvent.VK_O) {
//			OKey = false;
//		}
//		if (code == KeyEvent.VK_H) {
//			HKey = false;
//		}
	}

}
