package com.douglei.tools.utils.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 文件阅读工具类
 * @author DougLei
 */
public class FileReaderUtil {
	public static final String JAR_FILE = "_JAR_FILE_";
	private static final ClassLoader classLoader = FileReaderUtil.class.getClassLoader();
	
	/**
	 * 根据path读取文件, 获取文件字节流
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static InputStream readByPath(String path) {
		try {
			if(path.startsWith(JAR_FILE)) {
				return classLoader.getResourceAsStream(path.substring(JAR_FILE.length()));
			}
			return new FileInputStream(path);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("给定的["+path+"], 不存在任何文件");
		}
	}
}
