package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Entity {
	public int x, y;
	public float speed;
	public BufferedImage HP; // HP of entities
	public int HP_Left;
	
	ArrayList<BufferedImage> atk1 = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> atk2 = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> atk3 = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> air_atk = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> sp_atk = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> defend = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> run = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> jumpUp = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> jumpDown = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> idle = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> death = new ArrayList<BufferedImage>(); 
	ArrayList<BufferedImage> takeHit = new ArrayList<BufferedImage>(); 
}
