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
			//����·����ȡͼƬ
			BufferedImage image = ImageIO.read(new File("D:\\eclips.eworkspace\\tank1\\tank\\src\\images/9.gif"));			
			System.out.println("image:" + image.getClass().getName());
			
			//1.��ȡsrc��Ҳ����classpath�£������ļ������������
			//2.��classloaderȥ��ȡ�������
			//3.��ͼƬ�����ڴ���
			BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(image2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
