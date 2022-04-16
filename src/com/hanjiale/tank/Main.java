package com.hanjiale.tank;

import com.hanjiale.enums.Dir;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame = new TankFrame();
		
		//��ȡ�����ļ�����ȡ�з�̹�˵ĳ�ʼ����
		int count = Integer.parseInt((String)PropertyManager.get("initTankCount"));
		
		//��ʼ���з�̹��
		for (int i = 0; i < count; i++) {
			tankFrame.tanks.add(new Tank(50 + i*70, 200, Dir.DOWN,Group.BAD, tankFrame));
		}
		
		while(true) {
			Thread.sleep(50);
			//�÷������Զ�����paint����,ˢ�´���
			tankFrame.repaint();
		}
		
	}

}
