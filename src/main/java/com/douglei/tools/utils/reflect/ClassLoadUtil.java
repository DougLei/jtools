package com.douglei.tools.utils.reflect;

import com.douglei.tools.utils.UtilException;

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
	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClass(String className){
		try {
			return (Class<T>) Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new UtilException("没有找到className=["+className+"]的类");
		}
	}
	
	/**
	 * 加载class, 以及类中的static代码
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClassWithStatic(String className){
		try {
			return (Class<T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new UtilException("没有找到className=["+className+"]的类");
		}
	}
}
