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

/**
 * 类扫描器
 * @author StoneKing
 */
public class ClassScanner extends Scanner{
	
	// -----------------------------------------------------------------------------------------------------------
	@Override
	public List<String> scan(boolean searchAllPath, String basePackagePath) {
		if(StringUtil.isEmpty(basePackagePath)){
			throw new NullPointerException("basePackagePath 参数值不能为空");
		}
		
		String splashedPackageName = basePackagePath.replace(".", "/"); // 将包名的小数点，转换成url格式的分隔符，即'/'
		if(searchAllPath) {
			Enumeration<URL> fileUrls = getResources(splashedPackageName);
			while(fileUrls.hasMoreElements()) {
				scan_(fileUrls.nextElement(), basePackagePath, splashedPackageName);
			}
		}else {
			URL fileUrl = getResource(splashedPackageName); // 获取包在操作系统下的URL路径
			scan_(fileUrl, basePackagePath, splashedPackageName);
		}
		return list;
	}
	
	private void scan_(URL fileUrl, String basePackagePath, String splashedPackageName) {
		if(fileUrl != null) {
			String absoluteFilePath = getAbsoluteFilePath(fileUrl.getFile());
			if(isJarFile(absoluteFilePath)){
				scanFromJar(absoluteFilePath, splashedPackageName);
			}else{
				scanFromFile(absoluteFilePath, basePackagePath);
			}
		}
	}
	
	@Override
	public List<String> rescan(boolean searchAllPath, String basePackagePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(searchAllPath, basePackagePath);
	}
	
	@Override
	public List<String> multiScan(boolean searchAllPath, String... basePackagePaths){
		for (String basePackagePath : basePackagePaths) {
			scan(searchAllPath, basePackagePath);
		}
		return list;
	}
	
	@Override
	public List<String> reMultiScan(boolean searchAllPath, String... basePackagePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return multiScan(searchAllPath, basePackagePaths);
	}
	
	/**
	 * 从jar包中扫描类，并加入到list集合中
	 * @param filePath
	 * @param splashedPackageName
	 */
	private void scanFromJar(String filePath, String splashedPackageName) {
		JarInputStream jarInput = null;
		FileInputStream fis = null;
		JarEntry entry;
		String classFullName = null;
		try {
			fis = new FileInputStream(filePath);
			jarInput = new JarInputStream(fis);
			while((entry = jarInput.getNextJarEntry()) != null){ // 依次获取jar包中的每一个class文件，包括内部类，同时，是递归的方式获取，我们这里只要调用该方法即可
				classFullName = entry.getName();
				if(classFullName.startsWith(splashedPackageName) && isClassFile(classFullName)){
					list.add(classFullName.replace("/", ".").substring(0, classFullName.length()-6));
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("在扫描["+filePath+"]时, 出现异常:", e);
		} finally{
			CloseUtil.closeIO(jarInput, fis);
		}
	}
	
	/**
	 * 从文件中扫描类，并加入到list集合中
	 * @param filePath
	 * @param basePackagePath
	 */
	private void scanFromFile(String filePath, String basePackagePath) {
		String[] fileNames = new File(filePath).list();
		if(fileNames != null && fileNames.length > 0){
			for (String fn : fileNames) {
				if(isClassFile(fn)){
					list.add(getClassFullName(basePackagePath, fn));
				}else{
					scan(basePackagePath + "." + fn);
				}
			}
		}
	}
	
	/**
	 * 是否是class文件
	 * @param fileName
	 * @return
	 */
	private boolean isClassFile(String fileName) {
		return fileName.endsWith(".class");
	}
	
	/**
	 * 获取类全名
	 * @param basePackagePath
	 * @param classFileName
	 * @return
	 */
	private String getClassFullName(String basePackagePath, String classFileName) {
		String className = classFileName;
		int pos = classFileName.lastIndexOf(".");
		if(-1 != pos){
			className = classFileName.substring(0, pos);
		}
		return basePackagePath + "." + className;
	}
	
	@Override
	public List<String> scan(String basePackagePath) {
		return scan(false, basePackagePath);
	}

	@Override
	public List<String> rescan(String basePackagePath) {
		return rescan(false, basePackagePath);
	}

	@Override
	public List<String> multiScan(String... basePackagePaths) {
		return multiScan(false, basePackagePaths);
	}

	@Override
	public List<String> reMultiScan(String... basePackagePaths) {
		return reMultiScan(false, basePackagePaths);
	}
}
