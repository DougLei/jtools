package com.douglei.tools.reflect;

/**
 * 类的工具类
 * @author DougLei
 */
public class ClassUtil {
	
	/**
	 * 是否继承了指定的class
	 * @param clazz
	 * @param target
	 * @return
	 */
	public static boolean isExtendClass(Class<?> clazz, Class<?> target) {
		do {
			clazz = clazz.getSuperclass();
			if(clazz == Object.class)
				return false;
			if(clazz == target)
				return true;
		}while(true);
	}
	
	/**
	 * 是否实现了指定的interface
	 * @param clazz
	 * @param target
	 * @return
	 */
	public static boolean isImplementInterface(Class<?> clazz, Class<?> target) {
		if(target.isInterface()) {
			do {
				Class<?>[] interfaces = clazz.getInterfaces();
				if(interfaces.length > 0) {
					for (Class<?> interface_ : interfaces) {
						if(interface_ == target) 
							return true;
					}
				}
				clazz = clazz.getSuperclass();
			}while(clazz != Object.class);
		}
		return false;
	}
}
