package com.hanjiale.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.hanjiale.enums.Dir;

/**
 * �ӵ���
 * @author 13955
 *
 */
public class Bullet {
	
	/**
	 * �ӵ����ٶ�
	 */
	private static final int SPEED = 7;
	/**
	 * �ӵ��Ŀ�Ⱥ͸߶�
	 */
	public static final int WIDTH = ResourceImage.bulletD.getWidth();
	public static final int HEIGHT = ResourceImage.bulletD.getHeight();
	/**
	 * �ӵ���λ��
	 */
	private int x,y;
	/**
	 * �ӵ��ķ���
	 */
	private Dir dir;
	/**
	 * �ӵ��Ƿ񳬳��߽�
	 */
	private boolean live = true;
	/**
	 * �ӵ����ɺõ�̹�˷������ǻ���̹�˷���
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
		
		//�ӵ����γ�ʼ��
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
	 * ���ӵ�������
	 * @param g
	 */
	public void paint(Graphics g) {
		System.out.println("-----------------------------");
		if (!live) {
			tFrame.bullets.remove(this);
		}
		
//		Color  color = g.getColor();
//		g.setColor(Color.RED);
//		// ����Բ��
//		g.fillOval(x, y, WIDTH, HEIGHT);
//		g.setColor(color);
		
		switch (dir) {
		case LEFT:
			//��̹�˵�ͼƬ
			g.drawImage(ResourceImage.bulletL, x, y,null);
			break;
		case UP:
			//��̹�˵�ͼƬ
			g.drawImage(ResourceImage.bulletU, x, y,null);
			break;
		case RIGHT:
			//��̹�˵�ͼƬ
			g.drawImage(ResourceImage.bulletR, x, y,null);
			break;
		case DOWN:
			//��̹�˵�ͼƬ
			g.drawImage(ResourceImage.bulletD, x, y,null);
			break;
		default:
			break;
		}
		move();
	}
	
	/**
	 * �ӵ��ƶ�
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
		//�����ӵ����ƶ������¾��ε�λ��
		rectangle.x = this.x;
		rectangle.y = this.y;
		//�ӵ������߽���ʧ
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			live = false;
		}
		
	}

	/**
	 * �ж��ӵ���̹���Ƿ��ཻ
	 * @param tank
	 */
	public void collideWith(Tank tank) {	
		//ͬһ�������ܵ�����
		if (this.group == tank.getGroup()) {
			return;
		}
		//TODO:��һ��rectangle����¼�ӵ���λ��
//		Rectangle rect1 = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
//		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		//������������ཻ�����ӵ���̹�˶�����
		if (rectangle.intersects(tank.rectangle)) {
			tank.die();
			this.die();
			int initx = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int inity = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tFrame.explodes.add(new Explode(initx, inity, tFrame));
		}
	}
	
	/**
	 * ���ӵ���Ϊ����״̬
	 */
	private void die() {
		this.live = false;
	}
	
}
