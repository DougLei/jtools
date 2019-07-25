package com.douglei.tools.utils.reflect;

import com.douglei.tools.utils.UtilException;

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
			throw new UtilException("类["+className+"], 在调用无参构造函数, 创建其实例时出现异常", e);
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
			throw new UtilException("类["+clz.getName()+"], 在调用无参构造函数, 创建其实例时出现异常", e);
		}
	}
	
	/**
	 * 创建单例实例
	 * @param className
	 * @param singleInstanceMethodName 相应类中单例实例的静态方法名
	 * @return
	 */
	public static Object newSingleInstance(String className, String singleInstanceMethodName) {
		try {
			Class<?> clz = Class.forName(className);
			return clz.getMethod("singleInstance").invoke(null);
		} catch (Exception e) {
			throw new UtilException("类["+className+"], 在调用单例函数["+singleInstanceMethodName+"], 获取其(单例)实例时出现异常", e);
		}
	}
}
