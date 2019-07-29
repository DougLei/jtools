package com.douglei.tools.instances.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * 文件扫描器
 * @author StoneKing
 */
public class FileScanner extends Scanner{
	
	public FileScanner(String... targetFileSuffix) {
		super.targetFileSuffix = validateTargetFileSuffix(targetFileSuffix);
	}

	// -----------------------------------------------------------------------------------------------------------
	@Override
	public List<String> scan(boolean searchAllPath, String basePath) {
		if(basePath == null){
			throw new NullPointerException("basePath 参数值不能为空");
		}
		
		if(searchAllPath) {
			Enumeration<URL> fileUrls = getResources(basePath);
			while(fileUrls.hasMoreElements()) {
				scan_(fileUrls.nextElement(), basePath);
			}
		}else {
			URL fileUrl = getResource(basePath); // 获取文件在操作系统下的URL路径
			scan_(fileUrl, basePath);
		}
		return list;
	}
	
	private void scan_(URL fileUrl, String basePath) {
		if(fileUrl != null) {
			if(isFile(fileUrl)) {
				scanFromFile(fileUrl.getFile(), null);
			}else if(isJarFile(fileUrl)) {
				scanFromJar(fileUrl, basePath);
			}else {
				// TODO 后续可能需要实现其他protocol
				throw new UnsupportProtocolException(fileUrl);
			}
		}
	}
	
	@Override
	protected String processParamsOnDirectory(File file, String param) {
		return param;
	}

	@Override
	protected void addFileToList(File file, String param) {
		list.add(file.getAbsolutePath());
	}
	
	@Override
	protected void addJarEntryToList(JarEntry entry) {
		list.add(JAR_FILE_PREFIX + entry.getName());
	}
	
	@Override
	public List<String> rescan(boolean searchAllPath, String basePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(searchAllPath, basePath);
	}
	
	@Override
	public List<String> multiScan(boolean searchAllPath, String... basePaths){
		for (String basePath : basePaths) {
			scan(searchAllPath, basePath);
		}
		return list;
	}
	
	@Override
	public List<String> reMultiScan(boolean searchAllPath, String... basePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return multiScan(searchAllPath, basePaths);
	}
	
	@Override
	public List<String> scan(String basePath) {
		return scan(false, basePath);
	}

	@Override
	public List<String> rescan(String basePath) {
		return rescan(false, basePath);
	}

	@Override
	public List<String> multiScan(String... basePaths) {
		return multiScan(false, basePaths);
	}

	@Override
	public List<String> reMultiScan(String... basePaths) {
		return reMultiScan(false, basePaths);
	}
	
	// -----------------------------------------------------------------------------------------------------------
	private static final String JAR_FILE_PREFIX = "_JAR_FILE_";// 读取jar包中文件路径的前缀
	
	/**
	 * 根据扫描的path读取文件, 获取文件字节流
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static InputStream readByScanPath(String path) {
		InputStream in = null;
		try {
			if(path.startsWith(JAR_FILE_PREFIX)) {
				in = getClassLoader().getResourceAsStream(path.substring(JAR_FILE_PREFIX.length()));
				if(in == null) {
					throw new NullPointerException();
				}
			}else {
				in = new FileInputStream(path);
			}
			return in;
		} catch (Exception e) {
			throw new ScannerException("给定的["+path+"], 不存在任何文件");
		}
	}
}
