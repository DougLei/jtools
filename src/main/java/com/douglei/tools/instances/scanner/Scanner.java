package com.douglei.tools.instances.scanner;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author DougLei
 */
public abstract class Scanner {
	protected List<String> list = new LinkedList<String>();
	private static ClassLoader classLoader;
	
	private ClassLoader getClassLoader() {
		if(classLoader == null) {
			classLoader = getClass().getClassLoader();
		}
		return classLoader;
	}
	
	/**
	 * 
	 * @param basePath
	 * @return
	 */
	protected URL getResource(String basePath){
		return getClassLoader().getResource(basePath);
	}
	
	/**
	 * 
	 * @param basePath
	 * @return
	 */
	protected Enumeration<URL> getResources(String basePath){
		try {
			return getClassLoader().getResources(basePath);
		} catch (IOException e) {
			throw new RuntimeException("在扫描["+basePath+"]路径, getResources()时, 出现异常:", e);
		}
	}
	
	/**
	 * 根据文件的url，获取文件的绝对路径
	 * <pre>
	 * 	两种样式的fileUrlPath值
	 *  1: /C:/xxxxx/package...				【指定的是一个项目中存在的包路径】
	 *  2: file:/C:/xxxxx/x.jar!package...	【指定的是项目中某个jar包中的包路径】
	 * </pre>
	 * @param fileUrlPath
	 * @return
	 */
	protected String getAbsoluteFilePath(String fileUrlPath) {
		int pos = fileUrlPath.indexOf("!");
		if(-1 == pos){
			return fileUrlPath;
		}
		return fileUrlPath.substring(5, pos);
	}
	
	/**
	 * 是否是jar文件
	 * @param file
	 * @return
	 */
	protected boolean isJarFile(String fileName) {
		return fileName.endsWith(".jar");
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
	 * 根据包路径，扫描其下所有的类，获取它们的全名集合
	 * 
	 * 是否搜索相同的路径
	 * 
	 * searchSamePaths = false
	 * 扫描指定的某个路径时, 程序会先在当前项目中搜索该路径, 如果找到了, 则就在该路径下开始扫描目标, 同时, 如果jar中也存在相同的路径, 那么是不会被扫描的
	 * 											             如果没找到, 则就去jar中搜索, 如果搜索到了, 就在该jar的路径下开始扫描, 同时, 如果其他jar中也存在相同路径, 那么是不会被扫描的
	 * 即搜索到指定路径时, 就停止搜索其他相同的路径, 只扫描一个路径
	 * 
	 * searchSamePaths = true
	 * 即搜索所有指定的路径, 对所有满足条件的路径进行扫描
	 * 
	 * @param searchSamePaths
	 * @param basePath
	 * @return 
	 */
	public abstract List<String> scan(boolean searchSamePaths, String basePath);
	
	/**
	 * 根据包路径，重新扫描其下所有的类
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param basePath
	 * @return
	 */
	public abstract List<String> rescan(String basePath);
	/**
	 * 根据包路径，重新扫描其下所有的类
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param searchSamePaths @see scan(boolean searchSamePaths, String basePath)
	 * @param basePath
	 * @return
	 */
	public abstract List<String> rescan(boolean searchSamePaths, String basePath);
	
	/**
	 * 指定多个路径，多路径扫描，将最终的结果一次返回
	 * @param basePaths
	 * @return
	 */
	public abstract List<String> multiScan(String... basePaths);
	/**
	 * 指定多个路径，多路径扫描，将最终的结果一次返回
	 * @param searchSamePaths @see scan(boolean searchSamePaths, String basePath)
	 * @param basePaths
	 * @return
	 */
	public abstract List<String> multiScan(boolean searchSamePaths, String... basePaths);
	
	/**
	 * 根据包路径，重新循环扫描其下所有的类
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param basePaths
	 * @return
	 */
	public abstract List<String> reMultiScan(String... basePaths);
	/**
	 * 根据包路径，重新循环扫描其下所有的类
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param searchSamePaths @see scan(boolean searchSamePaths, String basePath)
	 * @param basePaths
	 * @return
	 */
	public abstract List<String> reMultiScan(boolean searchSamePaths, String... basePaths);
}
