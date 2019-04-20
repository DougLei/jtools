package com.douglei.instances.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.douglei.utils.CloseUtil;
import com.douglei.utils.StringUtil;

/**
 * 文件扫描器
 * @author StoneKing
 */
public class FileScanner {
	private FileScanner(){}
	private List<String> fileFullNames = new ArrayList<String>();
	
//	/**
//	 * 获取单例实例
//	 * @return
//	 */
//	public static final FileScanner getSingleInstance(){
//		if(singleInstance == null){
//			singleInstance = new FileScanner();
//		}
//		return singleInstance;
//	}
//	private static FileScanner singleInstance;
//	
//	/**
//	 * 创建实例
//	 * @return
//	 */
//	public static final FileScanner newInstance(){
//		return new FileScanner();
//	}
//	
//	// -----------------------------------------------------------------------------------------------------------
//	/**
//	 * 根据路径，重新扫描其下所有的文件
//	 * <p>会清空上一次扫描的文件路径结果集</p>
//	 * @param basePath
//	 * @return
//	 */
//	public List<String> rescanClasses(String basePath) {
//		if(!fileFullNames.isEmpty()){
//			fileFullNames.clear();
//		}
//		return scan(basePath);
//	}
//	
//	/**
//	 * 根据路径，扫描其下所有的文件，获取它们的绝对路径集合
//	 * @param basePath
//	 * @return 
//	 */
//	public List<String> scan(String basePath) {
//		if(StringUtil.isEmpty(basePath)){
//			throw new NullPointerException("basePath 参数值不能为空");
//		}
//		
//		URL fileUrl = getClass().getClassLoader().getResource(basePath); // 获取文件在操作系统下的URL路径
//		if(fileUrl == null){
//			throw new NullPointerException("路径 ["+basePath+"] 下不存在任何文件，请检查路径是否正确");
//		}
//		
//		recursiveScan(fileUrl.getFile(), basePath);
//		
//		if(fileFullNames.isEmpty()){
//			return null;
//		}
//		return fileFullNames;
//	}
//	
//	/**
//	 * 扫描文件，并加入到fileFullNames集合中
//	 * @param filePath
//	 * @param basePackagePath
//	 */
//	private void recursiveScan(String filePath, String basePackagePath) {
//		String[] fileNames = new File(filePath).list();
//		if(fileNames != null && fileNames.length > 0){
//			for (String fn : fileNames) {
//				if(isClassFile(fn)){
//					fileFullNames.add(getClassFullName(basePackagePath, fn));
//				}else{
//					scanClasses(basePackagePath + "." + fn);
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 是否是class文件
//	 * @param fileName
//	 * @return
//	 */
//	private boolean isTargetFile(String fileName) {
//		return fileName.endsWith(".class");
//	}
}
