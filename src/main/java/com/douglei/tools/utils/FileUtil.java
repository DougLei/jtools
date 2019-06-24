package com.douglei.tools.utils;

import java.io.File;

/**
 * 文件工具类
 * @author DougLei
 */
public class FileUtil {

	/**
	 * 获取<b>文件</b>对象, 如果文件上层的文件夹不存在则创建
	 * @param filePath 文件全路径  <b>包括文件名称</b>
	 * @return
	 */
	public static File getFile(String filePath) {
		File file = new File(filePath);
		
		File fileFolder = file.getParentFile();
		if(!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		return file;
	}
}
