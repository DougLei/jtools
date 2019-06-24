package com.douglei.tools.instances.scanner;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.douglei.tools.utils.StringUtil;

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
	public List<String> scan(String basePath) {
		if(StringUtil.isEmpty(basePath)){
			throw new NullPointerException("basePath 参数值不能为空");
		}
		
		URL fileUrl = getClassLoader().getResource(basePath); // 获取文件在操作系统下的URL路径
		if(fileUrl != null){
			recursiveScan(fileUrl.getFile());
		}
		return list;
	}
	
	@Override
	public List<String> rescan(String basePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(basePath);
	}
	
	@Override
	public List<String> multiScan(String... basePaths){
		for (String basePath : basePaths) {
			scan(basePath);
		}
		return list;
	}
	
	@Override
	public List<String> reMultiScan(String... basePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return multiScan(basePaths);
	}
	
	/**
	 * 扫描文件，并加入到list集合中
	 * @param filePath
	 */
	private void recursiveScan(String filePath) {
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
					recursiveScan(path);
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
}
