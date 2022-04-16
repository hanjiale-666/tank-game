package com.hanjiale.tank;

import com.hanjiale.enums.Dir;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame = new TankFrame();
		
		//读取配置文件，获取敌方坦克的初始数量
		int count = Integer.parseInt((String)PropertyManager.get("initTankCount"));
		
		//初始化敌方坦克
		for (int i = 0; i < count; i++) {
			tankFrame.tanks.add(new Tank(50 + i*70, 200, Dir.DOWN,Group.BAD, tankFrame));
		}
		
		while(true) {
			Thread.sleep(50);
			//该方法会自动调用paint方法,刷新窗口
			tankFrame.repaint();
		}
		
	}

}
