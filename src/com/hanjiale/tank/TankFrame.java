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
	 * 子弹数组
	 */
	List<Bullet> bullets = new ArrayList<Bullet>();
	/**
	 * 主战坦克
	 */
	Tank myTank = new Tank(200, 400, Dir.DOWN,Group.GOOD,this);
	/**
	 * 敌方坦克
	 */
	List<Tank> tanks = new ArrayList<Tank>();
		
	/**
	 * 游戏界面的宽高
	 */
	static int GAME_WIDTH = 1080,GAME_HEIGHT = 800;
	/**
	 * 爆炸集合
	 */
	List<Explode> explodes = new ArrayList<Explode>();


	public TankFrame() {
		// 设置窗口的宽和高
		setSize(GAME_WIDTH, GAME_HEIGHT);
		// 设置宽口的大小是不可变的
		setResizable(false);
		// 设置窗口标题
		setTitle("tank war");
		// 设置窗口是可见的
		setVisible(true);
		// 键盘监听事件
		this.addKeyListener(new MyKeyListener());

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 让窗口可以手动关闭
				System.exit(0);
			}
		});
	}
	
	/**
	 * 双缓冲解决坦克和子弹闪烁问题（现在内存中将要画的整个画面画好，画完后 ，在将整个画面一次性写到屏幕上）
	 * 截获update方法
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
	 * 改方法自动被调用，在创建的窗口被覆盖时，自动调用
	 */
	@Override
	public void paint(Graphics g) {
		System.out.println("-----------------------------");
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量：" + bullets.size(), 10, 60);
		g.drawString("敌方坦克的数量：" + tanks.size(), 10, 80);
		g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
		g.setColor(color);
		
		
		//让坦克根据自己的属性画
		myTank.paint(g);
		
		for (int i = 0; i < bullets.size(); i++) {
			//让子弹根据自己的属性画
			bullets.get(i).paint(g);
		}
		//画出所有敌方坦克
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		//爆炸集合画出来
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
		//判断每个子弹和每个坦克是否碰撞
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
	}

	/**
	 * 处理键盘事件处理
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
		 * 设置主站坦克向哪里移动
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
