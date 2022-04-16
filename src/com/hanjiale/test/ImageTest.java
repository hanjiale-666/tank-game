package com.hanjiale.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() {
		try {
			//绝对路径读取图片
			BufferedImage image = ImageIO.read(new File("D:\\eclips.eworkspace\\tank1\\tank\\src\\images/9.gif"));			
			System.out.println("image:" + image.getClass().getName());
			
			//1.获取src（也就是classpath下）下面文件的类加载器。
			//2.用classloader去读取下面的流
			//3.将图片读到内存中
			BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(image2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
