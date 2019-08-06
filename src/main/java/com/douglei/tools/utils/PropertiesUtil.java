package com.douglei.tools.utils;

import java.util.Properties;

/**
 * .properties文件工具类
 * @author DougLei
 */
public class PropertiesUtil {

	/**
	 * 获取属性值
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getProperty(Properties properties, String key) {
		return getProperty(properties, key, null);
	}
	
	/**
	 * 获取属性值
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(Properties properties, String key, String defaultValue) {
		String v = properties.getProperty(key);
		if(StringUtil.isEmpty(v)) {
			return defaultValue;
		}
		return v;
	}
}
