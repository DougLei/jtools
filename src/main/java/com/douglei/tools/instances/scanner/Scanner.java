package com.douglei.tools.instances.scanner;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public abstract class Scanner {
	private static final Logger logger = LoggerFactory.getLogger(Scanner.class);
	
	protected List<String> list = new LinkedList<String>();
	private ClassLoader classLoader;
	
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
			logger.error("在扫描[{}]路径, getResources()时, 出现异常: {}", basePath, ExceptionUtil.getExceptionDetailMessage(e));
		}
		return null;
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
	 */
	protected boolean searchSamePaths;
	public void setSearchSamePaths(boolean searchSamePaths) {
		this.searchSamePaths = searchSamePaths;
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
