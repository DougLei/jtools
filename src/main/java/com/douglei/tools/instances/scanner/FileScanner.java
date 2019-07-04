package com.douglei.tools.instances.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.file.FileReaderUtil;

/**
 * 文件扫描器
 * @author StoneKing
 */
public class FileScanner extends Scanner{
	private String[] targetFileSuffix;
	
	public FileScanner(String... targetFileSuffix) {
		if(targetFileSuffix != null && targetFileSuffix.length > 0) {
			this.targetFileSuffix = targetFileSuffix;
		}
	}

	// -----------------------------------------------------------------------------------------------------------
	@Override
	public List<String> scan(boolean searchAllPath, String basePath) {
		if(StringUtil.isEmpty(basePath)){
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
			String absoluteFilePath = getAbsoluteFilePath(fileUrl.getFile());
			if(isJarFile(absoluteFilePath)){
				scanFromJar(absoluteFilePath, basePath);
			}else{
				scanFromFile(absoluteFilePath);
			}
		}
	}
	
	/**
	 * 从jar包中扫描文件，并加入到list集合中
	 * @param filePath
	 * @param basePath
	 */
	private void scanFromJar(String filePath, String basePath) {
		JarInputStream jarInput = null;
		FileInputStream fis = null;
		JarEntry entry;
		String classFullName = null;
		try {
			fis = new FileInputStream(filePath);
			jarInput = new JarInputStream(fis);
			while((entry = jarInput.getNextJarEntry()) != null){ // 依次获取jar包中的每一个文件，包括内部类，同时，是递归的方式获取，我们这里只要调用该方法即可
				classFullName = entry.getName();
				if(classFullName.startsWith(basePath) && isTargetFile(classFullName)){
					list.add(FileReaderUtil.JAR_FILE + classFullName);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("在扫描["+filePath+"]时, 出现异常:", e);
		} finally{
			CloseUtil.closeIO(jarInput, fis);
		}
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
	
	/**
	 * 扫描文件，并加入到list集合中
	 * @param filePath
	 */
	private void scanFromFile(String filePath) {
		File firstFile = new File(filePath);
		if(firstFile.isFile()) {
			list.add(firstFile.getAbsolutePath());
			return;
		}
		
		File[] files = firstFile.listFiles();
		if(files != null && files.length > 0){
			String path = null;
			for (File f : files) {
				path = f.getAbsolutePath();
				if(f.isDirectory()) {
					scanFromFile(path);
				}else {
					if(isTargetFile(path)) {
						list.add(path);
					}
				}
				
			}
		}
	}
	
	/**
	 * 是否是要扫描的目标文件
	 * @param fileName
	 * @return
	 */
	private boolean isTargetFile(String fileName) {
		if(targetFileSuffix == null || targetFileSuffix.length == 0) {
			return true;
		}
		for (String fs : targetFileSuffix) {
			if(fileName.endsWith(fs)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 重置要扫描的目标文件后缀
	 * @param targetFileSuffix
	 */
	public void resetTargetFileSuffix(String... targetFileSuffix) {
		if(targetFileSuffix != null && targetFileSuffix.length > 0) {
			this.targetFileSuffix = targetFileSuffix;
		}else {
			this.targetFileSuffix = null;
		}
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
}
