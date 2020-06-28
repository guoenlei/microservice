package com.aldwx.app.common.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * 配置管理组件
 * @version
 */
public class ConfigurationManager {

	private static Properties prop = new Properties();

	static {
		try {
			InputStream in = ConfigurationManager.class
					.getClassLoader().getResourceAsStream("config.properties");
			// 从输入流中读取属性列表
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			prop.load(bf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	/**
	 * 获取整数类型的配置项
	 * @param key
	 * @return value
	 */
	public static Integer getInteger(String key) {
		String value = getProperty(key);
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取布尔类型的配置项
	 * @param key
	 * @return value
	 */
	public static Boolean getBoolean(String key) {
		String value = getProperty(key);
		try {
			return Boolean.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取Long类型的配置项
	 * @param key
	 * @return
	 */
	public static Long getLong(String key) {
		String value = getProperty(key);
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public static void main(String[] args) {
//		System.out.println(getProperty(Constants.JDBC_USER_PROD));
	}

}

