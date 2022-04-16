package com.hanjiale.tank;

import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 爆炸类
 * @author 13955
 *
 */
public class Explode {

	/**
	 * 爆炸图片的宽度和高度
	 */
	public  static final int WIDTH = ResourceImage.exploades[0].getWidth();
	public static final int HEIGHT = ResourceImage.exploades[0].getHeight();
	/**
	 * 爆炸图片的位置
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
		//从爆炸图片0开始画起
		g.drawImage(ResourceImage.exploades[step ++], x, y, null);
		//如果画完结束
		if (step >= ResourceImage.exploades.length) {
			tankFrame.explodes.remove(this);
		}
	}
}
