package com.douglei.tools.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author DougLei
 */
public class FileUtil {
	
	// 创建文件夹
	private static void mkdirs(File folder) {
		if(!folder.exists())
			folder.mkdirs();
	}
	
	/**
	 * 复制文件
	 * @param srcFile
	 * @param destFolder 这个是要复制的目标文件夹, 不是文件, 所以不要出现文件名
	 * @throws IOException 
	 */
	public static void copyFile(File srcFile, File destFolder) throws IOException {
		mkdirs(destFolder);
		try(BufferedInputStream reader=new BufferedInputStream(new FileInputStream(srcFile)); BufferedOutputStream writer=new BufferedOutputStream(new FileOutputStream(new File(destFolder.getAbsolutePath()+File.separatorChar+srcFile.getName())))){
			byte[] b = new byte[1024];
			short len;
			while((len =(short) reader.read(b)) != -1) {
				writer.write(b, 0, len);
			}
		}
	}
	
	/**
	 * 复制文件夹
	 * @param srcFolder
	 * @param destFolder
	 * @throws IOException 
	 */
	public static void copyFolder(File srcFolder, File destFolder) throws IOException {
		if(srcFolder.isFile()) {
			copyFile(srcFolder, destFolder);
		}else if(srcFolder.isDirectory()){
			mkdirs(destFolder);
			for (File file : srcFolder.listFiles()) {
				if(file.isFile()) {
					copyFile(file, destFolder);
				}else {
					copyFolder(file, new File(destFolder.getAbsolutePath()+File.separatorChar+file.getName()));
				}
			}
		}
	}
}
