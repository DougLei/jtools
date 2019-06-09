package com.douglei.tools.instances.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.StringUtil;

/**
 * 类扫描器
 * @author StoneKing
 */
public class ClassScanner extends AbstractScanner{
	
	// -----------------------------------------------------------------------------------------------------------
	@Override
	public List<String> scan(String basePackagePath) {
		if(StringUtil.isEmpty(basePackagePath)){
			throw new NullPointerException("basePackagePath 参数值不能为空");
		}
		
		String splashedPackageName = basePackagePath.replace(".", "/"); // 将包名的小数点，转换成url格式的分隔符，即'/'
		URL fileUrl = getClassLoader().getResource(splashedPackageName); // 获取包在操作系统下的URL路径
		if(fileUrl != null){
			String absoluteFilePath = getAbsoluteFilePath(fileUrl.getFile());
			if(isJarFile(absoluteFilePath)){
				scanFromJar(absoluteFilePath, splashedPackageName);
			}else{
				scanFromFile(absoluteFilePath, basePackagePath);
			}
		}
		return list;
	}
	
	@Override
	public List<String> rescan(String basePackagePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(basePackagePath);
	}
	
	@Override
	public List<String> multiScan(String... basePackagePaths){
		if(basePackagePaths != null && basePackagePaths.length > 0) {
			for (String basePackagePath : basePackagePaths) {
				scan(basePackagePath);
			}
		}
		return list;
	}
	
	@Override
	public List<String> reMultiScan(String... basePackagePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return multiScan(basePackagePaths);
	}
	
	/**
	 * 是否是jar文件
	 * @param file
	 * @return
	 */
	private boolean isJarFile(String fileName) {
		return fileName.endsWith(".jar");
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
	private String getAbsoluteFilePath(String fileUrlPath) {
		int pos = fileUrlPath.indexOf("!");
		if(-1 == pos){
			return fileUrlPath;
		}
		return fileUrlPath.substring(5, pos);
	}
	
	/**
	 * 从jar包中扫描类，并加入到list集合中
	 * @param filePath
	 * @param splashedPackageName
	 */
	private void scanFromJar(String filePath, String splashedPackageName) {
		JarInputStream jarInput = null;
		JarEntry entry;
		String classFullName = null;
		try {
			jarInput = new JarInputStream(new FileInputStream(filePath));
			while((entry = jarInput.getNextJarEntry()) != null){ // 依次获取jar包中的每一个class文件，包括内部类，同时，是递归的方式获取，我们这里只要调用该方法即可
				classFullName = entry.getName();
				if(classFullName.startsWith(splashedPackageName) && isClassFile(classFullName)){
					list.add(classFullName.replace("/", "."));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			CloseUtil.closeIO(jarInput);
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
}
