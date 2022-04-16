package com.hanjiale.tank;

import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ��ը��
 * @author 13955
 *
 */
public class Explode {

	/**
	 * ��ըͼƬ�Ŀ�Ⱥ͸߶�
	 */
	public  static final int WIDTH = ResourceImage.exploades[0].getWidth();
	public static final int HEIGHT = ResourceImage.exploades[0].getHeight();
	/**
	 * ��ըͼƬ��λ��
	 */
	private int x,y;
		
	TankFrame tankFrame = null;
	
	private int step = 0;
	
	ExecutorService ex = Executors.newFixedThreadPool(4);
	
	public Explode(int x,int y,TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.tankFrame = tFrame;
		//
		ex.submit(() ->  {
			new Audio("audio/explode.wav").play();
		});
	}
	
	public void paint(Graphics g) {
		//�ӱ�ըͼƬ0��ʼ����
		g.drawImage(ResourceImage.exploades[step ++], x, y, null);
		//����������
		if (step >= ResourceImage.exploades.length) {
			tankFrame.explodes.remove(this);
		}
	}
}
