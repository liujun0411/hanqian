package com.hanqian.util;

import java.util.Properties;

public class ConfigUtil {
	public ConfigUtil() {
		
	}
	
	/**
	 * 基本配置信息
	 */
	private static Properties config = null;
	
	
	/**
	 * 初始化基本配置
	 * proPath 配置文件路径
	 */
	private static void instanceConfig(String proPath) throws Exception {
		if(config == null || config.isEmpty()) {
			config = new Properties();
			
			config.load(ConfigUtil.class.getResourceAsStream(proPath));
		}
	}
	
	public static String getUrlConfig(String proPath,String key) throws Exception {
		instanceConfig(proPath);
		return config.getProperty(key);
	}
	
	public static String getConfig(String proPath,String key) throws Exception {
		instanceConfig(proPath);
		return config.getProperty(key);
	}
	
	
}
