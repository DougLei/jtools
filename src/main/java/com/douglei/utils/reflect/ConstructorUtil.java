package com.douglei.utils.reflect;

/**
 * 构造函数反射工具类
 * @author DougLei
 */
public class ConstructorUtil {
	
	/**
	 * 创建无参构造函数实例
	 * @param className
	 * @return
	 */
	public static Object newInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("类["+className+"], 在调用无参构造函数, 创建其实例时出现异常", e);
		}
	}
	
	/**
	 * 创建无参构造函数实例
	 * @param clz
	 * @return
	 */
	public static Object newInstance(Class<?> clz) {
		try {
			return clz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("类["+clz.getName()+"], 在调用无参构造函数, 创建其实例时出现异常", e);
		}
	}
}
