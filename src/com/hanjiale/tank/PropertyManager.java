package com.hanjiale.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件管理类
 * @author 13955
 *
 */
public class PropertyManager {

	
	static Properties prop = new Properties();
	
	static  {
		try {
			prop.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	/**
	 * 获取配置文件的值
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		if (prop.isEmpty() || key == null) {
			return null;
		}
		
		return prop.get(key);
	}
	
	public static void main(String[] args) {
		System.out.println(prop.get("initTankCount"));
	}
}
