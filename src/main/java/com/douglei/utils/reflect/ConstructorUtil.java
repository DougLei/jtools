package com.douglei.utils.reflect;

/**
 * 构造函数反射工具类
 * @author DougLei
 */
public class ConstructorUtil {
	
	/**
	 * 判断类是否存在
	 * @param className
	 * @return
	 */
	public static boolean classIsExists(String className) {
		try {
			// 在反射创建这个类的时候，不会调用到类的static块，属性，和方法，效率高于class.forName
			// 和loadClass正好相反，在只是判断一个类是否存在的情况下，不需要执行类的static块，属性，和方法，以提高效率
			Thread.currentThread().getContextClassLoader().loadClass(className);
//			Class.forName(classpath);// 在创建类的实例的时候，会调用类的static块，属性，和方法
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
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
