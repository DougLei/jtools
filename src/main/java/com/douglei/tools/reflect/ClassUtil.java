package com.douglei.tools.reflect;

/**
 * 类的工具类
 * @author DougLei
 */
public class ClassUtil {
	
	/**
	 * 加载class; 不会触发static代码块
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClass1(String name){
		try {
			return (Class<T>) Thread.currentThread().getContextClassLoader().loadClass(name);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("加载class时出现异常", e);
		}
	}
	
	/**
	 * 加载class; 会触发static代码块
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClass2(String name){
		try {
			return (Class<T>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("加载class时出现异常", e);
		}
	}
	
	/**
	 * 创建实例; 使用无参构造函数
	 * @param name
	 * @return
	 */
	public static Object newInstance(String name) {
		try {
			return Class.forName(name).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("创建实例时出现异常", e);
		}
	}
	
	/**
	 * 创建实例; 使用无参构造函数
	 * @param clazz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("创建实例时出现异常", e);
		}
	}
	
	
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
