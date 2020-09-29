package com.douglei.tools.instances.resource.scanner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.douglei.tools.instances.resource.scanner.impl.ResourceScanner;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractResourceScanner {
	private ClassLoader classloader;
	private PathDeDuplicationFilter filter; // 路径去重过滤器
	private String[] suffixes; // 要扫描的资源文件后缀
	
	protected List<String> list = new ArrayList<String>();
	
	protected AbstractResourceScanner(String separator, String... suffixes) {
		this.filter = new PathDeDuplicationFilter(separator);
		setSuffixes(suffixes);
	}
	
	
	/**
	 * 设置扫描时使用的类加载器
	 * @param classloader
	 */
	public void setClassloader(ClassLoader classloader) {
		this.classloader = classloader;
	}
	
	/**
	 * 设置要扫描的文件后缀
	 * @param suffixes
	 */
	public void setSuffixes(String... suffixes) {
		// 在设置扫描的目标资源文件后缀时顺便判断, 如果后缀没有以.开始, 则加上.
		if(suffixes != null && suffixes.length > 0) {
			for(int i=0;i<suffixes.length;i++) {
				if(suffixes[i].charAt(0) != '.') 
					suffixes[i] = "." + suffixes[i];
			}
			this.suffixes = suffixes;
		}else {
			this.suffixes = null;
		}
	}
	
	/**
	 * 扫描指定路径下的资源 
	 * @param path
	 * @return
	 */
	public List<String> scan(String path){
		return scan(false, path);
	}
	/**
	 * 扫描指定路径下的资源
	 * @param scanAll 是否扫描所有资源, 包括jar包中
	 * @param path
	 * @return
	 */
	public List<String> scan(boolean scanAll, String path){
		if(!list.isEmpty()) 
			list.clear();
		scan_(scanAll, path);
		return list;
	}
	
	/**
	 * 扫描多个指定路径下的资源
	 * @param paths
	 * @return
	 */
	public List<String> multiScan(String... paths){
		return multiScan(false, paths);
	}
	/**
	 * 扫描多个指定路径下的资源
	 * @param scanAll 是否扫描所有资源, 包括jar包中
	 * @param paths
	 * @return
	 */
	public List<String> multiScan(boolean scanAll, String... paths){
		if(!list.isEmpty()) 
			list.clear();
		paths = filter.deDuplication(paths);
		for (String path : paths) 
			scan_(scanAll, path);
		return list;
	}
	
	
	// --------------------------------------------------------------------------------
	/**
	 * 获取扫描时使用的类加载器
	 * @return
	 */
	protected ClassLoader getClassloader() {
		if(classloader == null)
			this.classloader = ResourceScanner.class.getClassLoader();
		return classloader;
	}
	
	/**
	 * 获取资源文件的类型
	 * @param url
	 * @return
	 */
	protected Type getType(URL url) {
		if(url.getProtocol().equals("file"))
			return Type.FILE;
		if(url.getProtocol().equals("jar"))
			return Type.JAR;
		throw new IllegalArgumentException("目前扫描器不支持处理 "+url.getProtocol()+" 类型的资源文件");
	}
	
	/**
	 * 判断是否是目标文件
	 * @param name
	 * @return
	 */
	protected boolean isTargetFile(String name) {
		if(suffixes == null) 
			return true;
		
		for (String suffix : suffixes) {
			if(name.endsWith(suffix)) 
				return true;
		}
		return false;
	}
	
	/**
	 * 扫描指定路径下的资源
	 * @param scanAll 是否扫描所有资源, 包括jar包中
	 * @param path
	 */
	protected abstract void scan_(boolean scanAll, String path);
}
