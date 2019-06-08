package com.douglei.utils.reflect;

/**
 * 
 * @author DougLei
 */
public class ClassLoadUtil {
	
	/**
	 * 加载class, 不加载类中的static代码
	 * @param className
	 * @return
	 */
	public static Class<?> loadClass(String className){
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("没有找到className=["+className+"]的类");
		}
	}
	
	/**
	 * 加载class, 以及类中的static代码
	 * @param className
	 * @return
	 */
	public static Class<?> loadClassWithStatic(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("没有找到className=["+className+"]的类");
		}
	}
}
