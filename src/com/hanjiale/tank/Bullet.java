package com.hanjiale.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.hanjiale.enums.Dir;

/**
 * 子弹类
 * @author 13955
 *
 */
public class Bullet {
	
	/**
	 * 子弹的速度
	 */
	private static final int SPEED = 7;
	/**
	 * 子弹的宽度和高度
	 */
	public static final int WIDTH = ResourceImage.bulletD.getWidth();
	public static final int HEIGHT = ResourceImage.bulletD.getHeight();
	/**
	 * 子弹的位置
	 */
	private int x,y;
	/**
	 * 子弹的方向
	 */
	private Dir dir;
	/**
	 * 子弹是否超出边界
	 */
	private boolean live = true;
	/**
	 * 子弹是由好的坦克发出还是坏的坦克发出
	 */
	private Group group = Group.BAD;
	
	Rectangle rectangle = new Rectangle();
	
	TankFrame tFrame = null;
	
	public Bullet(int x, int y, Dir dir,Group group,TankFrame tFrame) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tFrame = tFrame;
		
		//子弹矩形初始化
		rectangle.x = this.x;
		rectangle.y = this.y;
		rectangle.width = WIDTH;
		rectangle.height = HEIGHT;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * 将子弹画出来
	 * @param g
	 */
	public void paint(Graphics g) {
		System.out.println("-----------------------------");
		if (!live) {
			tFrame.bullets.remove(this);
		}
		
//		Color  color = g.getColor();
//		g.setColor(Color.RED);
//		// 画个圆形
//		g.fillOval(x, y, WIDTH, HEIGHT);
//		g.setColor(color);
		
		switch (dir) {
		case LEFT:
			//画坦克的图片
			g.drawImage(ResourceImage.bulletL, x, y,null);
			break;
		case UP:
			//画坦克的图片
			g.drawImage(ResourceImage.bulletU, x, y,null);
			break;
		case RIGHT:
			//画坦克的图片
			g.drawImage(ResourceImage.bulletR, x, y,null);
			break;
		case DOWN:
			//画坦克的图片
			g.drawImage(ResourceImage.bulletD, x, y,null);
			break;
		default:
			break;
		}
		move();
	}
	
	/**
	 * 子弹移动
	 */
	private void move() {
		
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
		//随着子弹的移动，更新矩形的位置
		rectangle.x = this.x;
		rectangle.y = this.y;
		//子弹超出边界消失
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			live = false;
		}
		
	}

	/**
	 * 判断子弹和坦克是否相交
	 * @param tank
	 */
	public void collideWith(Tank tank) {	
		//同一方不会受到攻击
		if (this.group == tank.getGroup()) {
			return;
		}
		//TODO:用一个rectangle来记录子弹的位置
//		Rectangle rect1 = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
//		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		//如果两个矩形相交，则子弹和坦克都死亡
		if (rectangle.intersects(tank.rectangle)) {
			tank.die();
			this.die();
			int initx = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int inity = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tFrame.explodes.add(new Explode(initx, inity, tFrame));
		}
	}
	
	/**
	 * 将子弹置为死亡状态
	 */
	private void die() {
		this.live = false;
	}
	
}
