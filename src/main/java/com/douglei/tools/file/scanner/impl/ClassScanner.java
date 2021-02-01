package com.douglei.tools.file.scanner.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.douglei.tools.UtilRuntimeException;
import com.douglei.tools.file.scanner.AbstractScanner;

/**
 * 类扫描器
 * @author StoneKing
 */
public class ClassScanner extends AbstractScanner{
	
	public ClassScanner() {
		super("\\.", ".class");
	}

	@Override
	public void setSuffixes(String... suffixes) {
		super.setSuffixes(".class");
	}

	
	@Override
	protected void scan_(boolean scanAll, String package_) {
		String path = package_.replace(".", "/"); // 将包路径中的.换成路径分隔符/
		if(scanAll) {
			try {
				Enumeration<URL> urls = getClassloader().getResources(path);
				while(urls.hasMoreElements()) 
					scan_(urls.nextElement(), path, package_);
			} catch (IOException e) {
				throw new UtilRuntimeException("在扫描["+path+"]路径, getResources()时, 出现异常:", e);
			}
		}else {
			scan_(getClassloader().getResource(path), path, package_);
		}
	}
	
	// 扫描指定路径下的资源
	private void scan_(URL url, String path, String package_) {
		if(url != null) {
			switch(getType(url)) {
				case FILE:
					scanFromFile(url.getFile(), package_);
					break;
				case JAR:
					scanFromJar(url, path, package_);
					break;
			}
		}
	}
	
	/**
	 * 扫描文件，并加入到list集合中
	 * @param path
	 * @param package_
	 */
	private void scanFromFile(String path, String package_) {
		File rootFile = new File(path);
		if(rootFile.isFile()) {
			if(isTargetFile(path)) 
				addFile(rootFile, package_);
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
					scanFromFile(file.getAbsolutePath(), package_ + "." + file.getName());
				 else 
					 addFile(file, package_);
			}
		}
	}
	private void addFile(File file, String package_) {
		String fileName = file.getName();
		list.add(package_ + "." + fileName.substring(0, fileName.length() - 6));
	}
	
	
	/**
	 * 扫描jar，并加入到list集合中
	 * @param url
	 * @param path
	 * @param package_
	 */
	private void scanFromJar(URL url, String path, String package_) {
		try {
			URLConnection urlConnection = url.openConnection();
			try(JarFile jarFile = ((JarURLConnection) urlConnection).getJarFile()){
				JarEntry entry = null;
				Enumeration<JarEntry> jarEntries = jarFile.entries();
				while(jarEntries.hasMoreElements()) {
					entry = jarEntries.nextElement();
					if(entry.getName().startsWith(path) && isTargetFile(entry.getName()))
						list.add(entry.getName().replace("/", ".").substring(0, entry.getName().length() - 6));
				}
			}
		} catch (IOException e) {
			throw new UtilRuntimeException("从jar扫描文件时出现异常", e);
		}
	}
}
