package com.douglei.tools.file.scanner.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.douglei.tools.UtilRuntimeException;
import com.douglei.tools.file.scanner.AbstractScanner;

/**
 * 资源扫描器
 * @author StoneKing
 */
public class ResourceScanner extends AbstractScanner{
	
	public ResourceScanner(String... suffixes) {
		super("/", suffixes);
	}
	
	@Override
	protected void scan_(boolean scanAll, String path) {
		if(path.indexOf("\\") != -1) // 将\路径分隔符改为/
			path = path.replace("\\", "/");
		
		if(scanAll) {
			try {
				Enumeration<URL> urls = getClassloader().getResources(path);
				while(urls.hasMoreElements()) 
					scan_(urls.nextElement(), path);
			} catch (IOException e) {
				throw new UtilRuntimeException("在扫描["+path+"]路径, getResources()时, 出现异常:", e);
			}
		}else {
			scan_(getClassloader().getResource(path), path);
		}
	}
	
	// 扫描指定路径下的资源
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
					list.add(file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * 扫描jar，并加入到list集合中
	 * @param url
	 * @param path
	 */
	private void scanFromJar(URL url, String path) {
		try {
			URLConnection urlConnection = url.openConnection();
			try(JarFile jarFile = ((JarURLConnection) urlConnection).getJarFile()){
				JarEntry entry = null;
				Enumeration<JarEntry> jarEntries = jarFile.entries();
				while(jarEntries.hasMoreElements()) {
					entry = jarEntries.nextElement();
					if(entry.getName().startsWith(path) && isTargetFile(entry.getName())) 
						list.add(JAR_FILE_PREFIX + entry.getName());
				}
			}
		} catch (Exception e) {
			throw new UtilRuntimeException("从jar扫描文件时出现异常", e);
		}
	}

	// -----------------------------------------------------------------------------------------------------------
	private static final String JAR_FILE_PREFIX = "_JAR_FILE_";
	
	/**
	 * 根据扫描的path读取文件, 获取文件字节流
	 * @param path
	 * @return
	 */
	public static final InputStream readByScanPath(String path) {
		try {
			if(path.startsWith(JAR_FILE_PREFIX)) 
				return ResourceScanner.class.getClassLoader().getResourceAsStream(path.substring(JAR_FILE_PREFIX.length()));
			return new FileInputStream(path);
		} catch (Exception e) {
			throw new UtilRuntimeException("readByScanPath时出现异常", e);
		}
	}
}
