package com.douglei.tools.utils.reflect;

import com.douglei.tools.utils.StringUtil;

/**
 * 验证工具类
 * @author DougLei
 */
public class ValidationUtil {
	
	/**
	 * clz是否实现了targetInterface
	 * @param clz
	 * @param targetInterface
	 * @return
	 */
	public static boolean isImplementInterface(Class<?> clz, Class<?> targetInterface) {
		Class<?>[] interfaces;
		do {
			interfaces = clz.getInterfaces();
			if(interfaces.length > 0) {
				for (Class<?> interface_ : interfaces) {
					if(interface_ == targetInterface) {
						return true;
					}
				}
			}
			clz = clz.getSuperclass();
		}while(clz != Object.class);
		return false;
	}
	
	/**
	 * 指定的class是否存在
	 * @param classpath
	 * @return
	 */
	public static boolean classExists(String classpath) {
		if(StringUtil.notEmpty(classpath)) {
			try {
				Thread.currentThread().getContextClassLoader().loadClass(classpath);
				return true;
			} catch (ClassNotFoundException e) {
				return false;
			}
		}
		return false;
	}
}
