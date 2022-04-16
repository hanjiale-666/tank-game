package com.hanjiale.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.hanjiale.enums.Dir;


public class TankFrame extends Frame {

	/**
	 * �ӵ�����
	 */
	List<Bullet> bullets = new ArrayList<Bullet>();
	/**
	 * ��ս̹��
	 */
	Tank myTank = new Tank(200, 400, Dir.DOWN,Group.GOOD,this);
	/**
	 * �з�̹��
	 */
	List<Tank> tanks = new ArrayList<Tank>();
		
	/**
	 * ��Ϸ����Ŀ��
	 */
	static int GAME_WIDTH = 1080,GAME_HEIGHT = 800;
	/**
	 * ��ը����
	 */
	List<Explode> explodes = new ArrayList<Explode>();


	public TankFrame() {
		// ���ô��ڵĿ�͸�
		setSize(GAME_WIDTH, GAME_HEIGHT);
		// ���ÿ�ڵĴ�С�ǲ��ɱ��
		setResizable(false);
		// ���ô��ڱ���
		setTitle("tank war");
		// ���ô����ǿɼ���
		setVisible(true);
		// ���̼����¼�
		this.addKeyListener(new MyKeyListener());

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// �ô��ڿ����ֶ��ر�
				System.exit(0);
			}
		});
	}
	
	/**
	 * ˫������̹�˺��ӵ���˸���⣨�����ڴ��н�Ҫ�����������滭�ã������ ���ڽ���������һ����д����Ļ�ϣ�
	 * �ػ�update����
	 */
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	
	}

	/**
	 * �ķ����Զ������ã��ڴ����Ĵ��ڱ�����ʱ���Զ�����
	 */
	@Override
	public void paint(Graphics g) {
		System.out.println("-----------------------------");
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("�ӵ���������" + bullets.size(), 10, 60);
		g.drawString("�з�̹�˵�������" + tanks.size(), 10, 80);
		g.drawString("��ը��������" + explodes.size(), 10, 100);
		g.setColor(color);
		
		
		//��̹�˸����Լ������Ի�
		myTank.paint(g);
		
		for (int i = 0; i < bullets.size(); i++) {
			//���ӵ������Լ������Ի�
			bullets.get(i).paint(g);
		}
		//�������ез�̹��
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		//��ը���ϻ�����
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
		//�ж�ÿ���ӵ���ÿ��̹���Ƿ���ײ
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
	}

	/**
	 * ��������¼�����
	 * 
	 * @author 13955
	 */
	class MyKeyListener extends KeyAdapter {
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("key pressed");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;

			default:
				break;
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("key released");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;
				
			default:
				break;
			}
			setMainTankDir();
		}
		/**
		 * ������վ̹���������ƶ�
		 */
		private void setMainTankDir() {
			
			if (!bL && !bU && !bR && !bD) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if (bL) {
					myTank.setDir(Dir.LEFT);
				}
				if (bU) {
					myTank.setDir(Dir.UP);
				}
				if (bR) {
					myTank.setDir(Dir.RIGHT);
				}
				if (bD) {
					myTank.setDir(Dir.DOWN);
				}
			}
		}


	}

}
