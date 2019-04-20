package com.douglei.instances.scanner;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.douglei.utils.StringUtil;

/**
 * 文件扫描器
 * @author StoneKing
 */
public class FileScanner {
	private FileScanner(){}
	private List<String> fileFullNames = new ArrayList<String>();
	
	private String[] targetFileSuffix;
	
	/**
	 * 获取单例实例
	 * @return
	 */
	public static final FileScanner getSingleInstance(){
		if(singleInstance == null){
			singleInstance = new FileScanner();
		}
		return singleInstance;
	}
	private static FileScanner singleInstance;
	
	/**
	 * 创建实例
	 * @return
	 */
	public static final FileScanner newInstance(){
		return new FileScanner();
	}
	
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 根据路径，重新扫描其下所有的文件
	 * <p>会清空上一次扫描的文件路径结果集</p>
	 * @param basePath
	 * @param targetFileSuffix
	 * @return
	 */
	public List<String> rescanClasses(String basePath, String... targetFileSuffix) {
		if(!fileFullNames.isEmpty()){
			fileFullNames.clear();
		}
		return scan(basePath, targetFileSuffix);
	}
	
	/**
	 * 根据路径，扫描其下所有的文件，获取它们的绝对路径集合
	 * @param basePath
	 * @param targetFileSuffix
	 * @return 
	 */
	public List<String> scan(String basePath, String... targetFileSuffix) {
		if(StringUtil.isEmpty(basePath)){
			throw new NullPointerException("basePath 参数值不能为空");
		}
		if(targetFileSuffix != null) {
			this.targetFileSuffix = targetFileSuffix;
		}
		
		URL fileUrl = getClass().getClassLoader().getResource(basePath); // 获取文件在操作系统下的URL路径
		if(fileUrl == null){
			throw new NullPointerException("路径 ["+basePath+"] 下不存在任何文件，请检查路径是否正确");
		}
		
		recursiveScan(fileUrl.getFile());
		return fileFullNames;
	}
	
	/**
	 * 扫描文件，并加入到fileFullNames集合中
	 * @param filePath
	 */
	private void recursiveScan(String filePath) {
		File[] files = new File(filePath).listFiles();
		if(files != null && files.length > 0){
			String path = null;
			for (File f : files) {
				path = f.getAbsolutePath();
				if(f.isDirectory()) {
					recursiveScan(path);
				}else {
					if(isTargetFile(path)) {
						fileFullNames.add(path);
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
}
