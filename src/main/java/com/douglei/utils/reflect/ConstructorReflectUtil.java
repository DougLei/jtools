package com.douglei.utils.reflect;

/**
 * 构造函数反射工具类
 * @author DougLei
 */
public class ConstructorReflectUtil {
	
	/**
	 * 创建无参构造函数实例
	 * @param clz
	 * @return
	 */
	public static Object newInstance(Class<?> clz) {
		try {
			return clz.getConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("根据类["+clz.getName()+"]的无参构造函数, new实例时, 出现异常", e);
		}
	}
}
