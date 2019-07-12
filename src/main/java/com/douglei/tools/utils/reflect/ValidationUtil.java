package com.douglei.tools.utils.reflect;

/**
 * 验证工具类
 * @author DougLei
 */
public class ValidationUtil {
	
	/**
	 * 是否实现了targetInterface
	 * @param targetInterface
	 * @param interfaces
	 * @return
	 */
	public static boolean isImplementInterface(Class<?> targetInterface, Class<?>[] interfaces) {
		if(targetInterface != null && interfaces != null && interfaces.length > 0) {
			for (Class<?> interface_ : interfaces) {
				if(interface_ == targetInterface) {
					return true;
				}
			}
		}
		return false;
	}
}
