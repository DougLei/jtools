package com.douglei.instances.scanner;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.douglei.utils.StringUtil;

/**
 * 文件扫描器
 * @author StoneKing
 */
public class FileScanner extends AbstractScanner{
	private String[] targetFileSuffix;
	
	public FileScanner(String... targetFileSuffix) {
		if(targetFileSuffix != null && targetFileSuffix.length > 0) {
			this.targetFileSuffix = targetFileSuffix;
		}
	}

	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 根据路径，扫描其下所有的文件，获取它们的绝对路径集合
	 * @param basePath
	 * @param targetFileSuffix
	 * @return 
	 */
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
	
	/**
	 * 根据路径，重新扫描其下所有的文件
	 * <p>会清空上一次扫描的文件路径结果集</p>
	 * @param basePath
	 * @return
	 */
	@Override
	public List<String> rescan(String basePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(basePath);
	}
	
	/**
	 * 指定多个路径，循环扫描，将最终的结果一次返回
	 * @param basePaths
	 * @return
	 */
	@Override
	public List<String> loopScan(String... basePaths){
		if(basePaths != null && basePaths.length > 0) {
			for (String basePath : basePaths) {
				scan(basePath);
			}
		}
		return list;
	}
	
	/**
	 * 根据路径，重新循环扫描其下所有的文件
	 * <p>会清空上一次扫描的类全名结果集</p>
	 * @param basePaths
	 * @return
	 */
	@Override
	public List<String> reLoopScan(String... basePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return loopScan(basePaths);
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
