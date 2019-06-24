package com.douglei.tools.instances.scanner;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author DougLei
 */
public abstract class Scanner {
	protected List<String> list = new LinkedList<String>();
	private ClassLoader classLoader;
	
	protected ClassLoader getClassLoader() {
		if(classLoader == null) {
			classLoader = getClass().getClassLoader();
		}
		return classLoader;
	}
	
	public List<String> getResult(){
		return list;
	}
	
	/**
	 * 销毁对象
	 */
	public void destroy() {
		list.clear();
		list = null;
	}
	
	/**
	 * 根据包路径，扫描其下所有的类，获取它们的全名集合
	 * @param basePath
	 * @return 
	 */
	public abstract List<String> scan(String basePath);
	
	/**
	 * 根据包路径，重新扫描其下所有的类
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param basePath
	 * @return
	 */
	public abstract List<String> rescan(String basePath);
	
	/**
	 * 指定多个路径，多路径扫描，将最终的结果一次返回
	 * @param basePaths
	 * @return
	 */
	public abstract List<String> multiScan(String... basePaths);
	
	/**
	 * 根据包路径，重新循环扫描其下所有的类
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param basePaths
	 * @return
	 */
	public abstract List<String> reMultiScan(String... basePaths);
}