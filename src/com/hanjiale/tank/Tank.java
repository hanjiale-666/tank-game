package com.hanjiale.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.hanjiale.enums.Dir;
import com.hanjiale.tank.TankFrame;

/**
 * 坦克实体类
 * @author 13955
 */
public class Tank {
	
	/**
	 * 坦克初始位置
	 */
	private int x,y;
	/**
	 * 坦克初始移动方向
	 */
	private Dir dir  = Dir.DOWN;
	/**
	 * 是否在移动状态
	 */
	private boolean moving = true;
	/**
	 * 坦克移动速度
	 */
	private static final int SPEED = 6;
	/**
	 * 坦克是否活着
	 */
	private boolean living = true;
	/**
	 * 坦克默认是坏的分组
	 */
	private Group group = Group.BAD;
	/**
	 * 坦克的宽度和高度
	 */
	public  static final int WIDTH = ResourceImage.goodTankU.getWidth();
	public static final int HEIGHT = ResourceImage.goodTankU.getHeight();
	
	Rectangle rectangle = new Rectangle();
	
	public Random random = new Random();

	private TankFrame thFrame = null;
	
	
	public Tank(int x, int y, Dir dir,Group group,TankFrame tFrame) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.thFrame = tFrame;
		
		//坦克矩形初始化
		rectangle.x = this.x;
		rectangle.y = this.y;
		rectangle.width = WIDTH;
		rectangle.height = HEIGHT;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * 将坦克画出来
	 * @param g
	 */
	public void paint(Graphics g) {
		System.out.println("-----------------------------");
		/*
		 * Color color = g.getColor(); //设置 坦克颜色为黄色 g.setColor(Color.YELLOW); // 画个矩形
		 * g.fillRect(x, y, WIDTH, HEIGHT); //在将画笔颜色设置 为默认颜色 g.setColor(color);
		 */
		if (!living) {
			thFrame.tanks.remove(this);
		}
		
		switch (dir) {
		case LEFT:
			//画坦克的图片
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankL : ResourceImage.badTankL, x, y,null);
			break;
		case UP:
			//画坦克的图片
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankU : ResourceImage.badTankU, x, y,null);
			break;
		case RIGHT:
			//画坦克的图片
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankR : ResourceImage.badTankR, x, y,null);
			break;
		case DOWN:
			//画坦克的图片
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankD : ResourceImage.badTankD, x, y,null);
			break;
		default:
			break;
		}
		//让坦克移动
		move();
	}

	/**
	 * 让坦克移动
	 */
	private void move() {
		if (!moving) {
			return;
		}
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;

		default:
			break;
		}
		
		//随着坦克的移动，更新矩形的位置
		rectangle.x = this.x;
		rectangle.y = this.y;
		//敌方坦克发射炮弹的概率为80%
		if (this.group == Group.BAD && random.nextInt(100) > 80) {
			this.fire();
		}
		//敌方坦克随机方向移动
		if (this.group == Group.BAD && random.nextInt(100) > 80) {
			randomDir();
		}
		//边界检测
		boundCheck();
	}
	/**
	 * 坦克移动到边界禁止
	 */
	public void boundCheck() {
		if (this.x < 0) {
			x = 0;
		}
		if (this.y <  30) {
			y = 30;
		}
		if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
			x = TankFrame.GAME_WIDTH - Tank.WIDTH;
		}
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
			y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
		}
	}
	/**
	 * 敌方坦克随机移动方向
	 */
	public void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	/**
	 * 坦克发射子弹
	 */
	public void fire() {
		int initx = 0;
		int inity = 0;
		switch (dir) {
		case LEFT:
			initx = this.x + WIDTH/2 - ResourceImage.bulletR.getWidth()/2;
			inity = this.y + HEIGHT/2 - ResourceImage.bulletR.getHeight()/2 -1;
			break;
		case UP:
			initx = this.x + WIDTH/2 - Bullet.WIDTH/2 + 1;
			inity = this.y + HEIGHT/2 - Bullet.HEIGHT/2;
			break;
		case RIGHT:
			initx = this.x + WIDTH/2 - ResourceImage.bulletR.getWidth()/2;
			inity = this.y + HEIGHT/2 - ResourceImage.bulletR.getHeight()/2;
			break;
		case DOWN:
			initx = this.x + WIDTH/2 - Bullet.WIDTH/2 - 1;
			inity = this.y + HEIGHT/2 - Bullet.HEIGHT/2;
			break;

		default:
			break;
		}
		thFrame.bullets.add(new Bullet(initx, inity, this.dir,this.group,thFrame));
	}
	/**
	 * 将坦克置为死亡状态
	 */
	public void die() {
		this.living = false;
	}
}
