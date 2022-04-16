package com.hanjiale.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.hanjiale.enums.Dir;
import com.hanjiale.tank.TankFrame;

/**
 * ̹��ʵ����
 * @author 13955
 */
public class Tank {
	
	/**
	 * ̹�˳�ʼλ��
	 */
	private int x,y;
	/**
	 * ̹�˳�ʼ�ƶ�����
	 */
	private Dir dir  = Dir.DOWN;
	/**
	 * �Ƿ����ƶ�״̬
	 */
	private boolean moving = true;
	/**
	 * ̹���ƶ��ٶ�
	 */
	private static final int SPEED = 6;
	/**
	 * ̹���Ƿ����
	 */
	private boolean living = true;
	/**
	 * ̹��Ĭ���ǻ��ķ���
	 */
	private Group group = Group.BAD;
	/**
	 * ̹�˵Ŀ�Ⱥ͸߶�
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
		
		//̹�˾��γ�ʼ��
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
	 * ��̹�˻�����
	 * @param g
	 */
	public void paint(Graphics g) {
		System.out.println("-----------------------------");
		/*
		 * Color color = g.getColor(); //���� ̹����ɫΪ��ɫ g.setColor(Color.YELLOW); // ��������
		 * g.fillRect(x, y, WIDTH, HEIGHT); //�ڽ�������ɫ���� ΪĬ����ɫ g.setColor(color);
		 */
		if (!living) {
			thFrame.tanks.remove(this);
		}
		
		switch (dir) {
		case LEFT:
			//��̹�˵�ͼƬ
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankL : ResourceImage.badTankL, x, y,null);
			break;
		case UP:
			//��̹�˵�ͼƬ
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankU : ResourceImage.badTankU, x, y,null);
			break;
		case RIGHT:
			//��̹�˵�ͼƬ
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankR : ResourceImage.badTankR, x, y,null);
			break;
		case DOWN:
			//��̹�˵�ͼƬ
			g.drawImage(this.group == Group.GOOD ? ResourceImage.goodTankD : ResourceImage.badTankD, x, y,null);
			break;
		default:
			break;
		}
		//��̹���ƶ�
		move();
	}

	/**
	 * ��̹���ƶ�
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
		
		//����̹�˵��ƶ������¾��ε�λ��
		rectangle.x = this.x;
		rectangle.y = this.y;
		//�з�̹�˷����ڵ��ĸ���Ϊ80%
		if (this.group == Group.BAD && random.nextInt(100) > 80) {
			this.fire();
		}
		//�з�̹����������ƶ�
		if (this.group == Group.BAD && random.nextInt(100) > 80) {
			randomDir();
		}
		//�߽���
		boundCheck();
	}
	/**
	 * ̹���ƶ����߽��ֹ
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
	 * �з�̹������ƶ�����
	 */
	public void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	/**
	 * ̹�˷����ӵ�
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
	 * ��̹����Ϊ����״̬
	 */
	public void die() {
		this.living = false;
	}
}
