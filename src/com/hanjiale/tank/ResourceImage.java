package com.hanjiale.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceImage {
	
	/**
	 * 上下左右方向的坦克图片
	 */
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD; 
	public static BufferedImage badTankL, badTankU, badTankR, badTankD; 
	/**
	 * 上下左右方向的子弹图片
	 */
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	/**
	 * 爆炸的图片列表
	 */
	public static BufferedImage[] exploades = new BufferedImage[16];

	static {
		try {
			// 加载坦克的图片
			goodTankU = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			badTankU = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			/*
			 * tankL =
			 * ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream(
			 * "images/tankL.gif")); tankU =
			 * ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream(
			 * "images/tankU.gif")); tankR =
			 * ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream(
			 * "images/tankR.gif")); tankD =
			 * ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream(
			 * "images/tankD.gif"));
			 */
			// 加载子弹的图片
			bulletU = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
//			bulletL = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
//			bulletU = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
//			bulletR = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
//			bulletD = ImageIO.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

			for (int i = 0; i < 16; i++) {
				exploades[i] = ImageIO
						.read(ResourceImage.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
