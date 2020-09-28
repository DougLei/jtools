package com.douglei.tools.instances.scanner.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.instances.scanner.PathDeDuplicationFilter;
import com.douglei.tools.instances.scanner.ScannerException;
import com.douglei.tools.instances.scanner.UnsupportUrlConnectionException;
import com.douglei.tools.utils.CloseUtil;

/**
 * 资源扫描器
 * @author StoneKing
 */
public class ResourceScanner {
	private static final Logger logger = LoggerFactory.getLogger(ResourceScanner.class);
	protected ClassLoader classloader = ResourceScanner.class.getClassLoader();
	protected static PathDeDuplicationFilter filter = new PathDeDuplicationFilter();

	protected List<String> list = new ArrayList<String>();
	protected String[] suffixes; // 要扫描的资源文件后缀
	
	public ResourceScanner(String... suffixes) {
		setSuffixes(suffixes);
	}
	
	// 在设置扫描的目标资源文件后缀时顺便判断, 如果后缀没有以.开始, 则加上.
	private void setSuffixes(String... suffixes) {
		if(suffixes != null && suffixes.length > 0) {
			for(int i=0;i<suffixes.length;i++) {
				if(suffixes[i].charAt(0) != '.') {
					suffixes[i] = "." + suffixes[i];
				}
			}
			this.suffixes = suffixes;
		}
	}
	
	/**
	 * 重置要扫描的资源文件后缀
	 * @param suffixes
	 */
	public void resetSuffixes(String... suffixes) {
		if(this.suffixes != null)
			this.suffixes = null;
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
	 * 获取扫描的结果集
	 * @return
	 */
	public List<String> getResult(){
		return list;
	}
	
	// -----------------------------------------------------------------------------------------------------------
	// 获取资源文件的类型
	private Type getType(URL url) {
		if(url.getProtocol().equals("file"))
			return Type.FILE;
		if(url.getProtocol().equals("jar"))
			return Type.JAR;
		throw new IllegalArgumentException("目前扫描器不支持处理 "+url.getProtocol()+" 类型的资源文件");
	}
	
	// 内部扫描方法, 要区分url的类型
	private void scan_(URL url, String path) {
		if(url != null) {
			switch(getType(url)) {
				case FILE:
					scanFromFile(url.getFile());
					break;
				case JAR:
					scanFromJar(url, path);
					break;
			}
		}
	}
	
	/**
	 * 扫描文件，并加入到list集合中
	 * @param path
	 */
	private void scanFromFile(String path) {
		File rootFile = new File(path);
		if(rootFile.isFile()) {
			if(isTargetFile(path)) 
				list.add(rootFile.getAbsolutePath());
			return;
		}
		
		// 获取符合条件的文件(夹)数组
		File[] files = rootFile.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if(file.isDirectory() || isTargetFile(file.getName()))
					return true;
				return false;
			}
		});
		if(files != null && files.length > 0){
			for (File file : files) {
				if(file.isDirectory()) 
					scanFromFile(file.getAbsolutePath());
				else
					list.add(rootFile.getAbsolutePath());
			}
		}
	}
	
	/**
	 * 是否是目标文件
	 * @param name
	 * @return
	 */
	private boolean isTargetFile(String name) {
		if(suffixes == null) 
			return true;
		
		for (String suffix : suffixes) {
			if(name.endsWith(suffix)) 
				return true;
		}
		return false;
	}
	
	
	/**
	 * 从jar包中扫描文件
	 * @param url
	 * @param path
	 */
	private void scanFromJar(URL url, String path) {
		JarFile jarFile = null;
		JarEntry entry = null;
		try {
			URLConnection urlConnection = url.openConnection();
			if(urlConnection instanceof JarURLConnection) {
				jarFile = ((JarURLConnection) urlConnection).getJarFile();
				Enumeration<JarEntry> jarEntries = jarFile.entries();
				while(jarEntries.hasMoreElements()) {
					entry = jarEntries.nextElement();
					if(entry.getName().startsWith(path) && isTargetFile(entry.getName())) 
						list.add(JAR_FILE_PREFIX + entry.getName());
				}
			}else {
				// TODO 后续可能需要实现其他类型
				throw new UnsupportUrlConnectionException(urlConnection);
			}
		} catch (Exception e) {
			throw new ScannerException("从jar扫描文件时出现异常", e);
		} finally {
			CloseUtil.closeIO(jarFile);
		}
	}
	
	
	/**
	 * 扫描指定路径下的资源 
	 * @param path
	 * @return
	 */
	public List<String> scan(String path) {
		return scan(false, path);
	}
	/**
	 * 扫描指定路径下的资源
	 * @param scanAll 是否扫描所有资源, 包括jar包中
	 * @param path
	 * @return
	 */
	public List<String> scan(boolean scanAll, String path) {
		if(path.indexOf("\\") != -1) 
			path = path.replace("\\", "/");
		
		if(scanAll) {
			try {
				Enumeration<URL> urls = classloader.getResources(path);
				while(urls.hasMoreElements()) 
					scan_(urls.nextElement(), path);
			} catch (IOException e) {
				logger.error("在扫描[{}]路径, getResources()时, 出现异常:");
			}
		}else {
			scan_(classloader.getResource(path), path);
		}
		return list;
	}
	
	/**
	 * 重新扫描指定路径下的资源
	 * @param path
	 * @return
	 */
	public List<String> rescan(String path) {
		if(!list.isEmpty()) 
			list.clear();
		return scan(false, path);
	}
	/**
	 * 重新扫描指定路径下的资源
	 * @param scanAll 是否扫描所有资源, 包括jar包中
	 * @param path
	 * @return
	 */
	public List<String> rescan(boolean scanAll, String path) {
		if(!list.isEmpty()) 
			list.clear();
		return scan(scanAll, path);
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
		paths = filter.doFilter(paths);
		for (String path : paths) {
			scan(scanAll, path);
		}
		return list;
	}
	
	/**
	 * 重新扫描多个指定路径下的资源
	 * @param paths
	 * @return
	 */
	public List<String> remultiScan(String... paths) {
		return remultiScan(false, paths);
	}
	/**
	 * 重新扫描多个指定路径下的资源
	 * @param scanAll 是否扫描所有资源, 包括jar包中
	 * @param paths
	 * @return
	 */
	public List<String> remultiScan(boolean scanAll, String... paths) {
		if(!list.isEmpty()) 
			list.clear();
		return multiScan(scanAll, paths);
	}
	
	

	
	
	
	
	
	@Override
	protected int pathSplitLength(String path) {
		return path.split("/").length;
	}
	
	// -----------------------------------------------------------------------------------------------------------
	private static final String JAR_FILE_PREFIX = "_JAR_FILE_";
	
	/**
	 * 根据扫描的path读取文件, 获取文件字节流
	 * @param path
	 * @return
	 */
	public static InputStream readByScanPath(String path) {
		InputStream in = null;
		try {
			if(path.startsWith(JAR_FILE_PREFIX)) {
				in = ResourceScanner.class.getClassLoader().getResourceAsStream(path.substring(JAR_FILE_PREFIX.length()));
				if(in == null) 
					throw new NullPointerException();
			}else {
				in = new FileInputStream(path);
			}
			return in;
		} catch (Exception e) {
			throw new ScannerException("给定的["+path+"], 不存在任何文件");
		}
	}
}
