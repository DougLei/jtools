package com.douglei.tools.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.ExceptionUtil;

/**
 * 文件工具类
 * @author DougLei
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 复制, 从输入流到输出流
	 * @param in
	 * @param out
	 */
	public static void copy(InputStream in, OutputStream out) {
		try(BufferedInputStream reader = new BufferedInputStream(in)){
			int len;
			byte[] b = new byte[1024];
			while((len = reader.read(b)) > 0) 
				out.write(b, 0, len);
		} catch (IOException e) {
			logger.error("复制时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	
	/**
	 * 复制文件
	 * @param srcFile
	 * @param out
	 */
	public static void copyFile(File srcFile, OutputStream out) {
		try {
			copy(new FileInputStream(srcFile), out);
		} catch (FileNotFoundException e) {
			logger.error("复制文件时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	
	/**
	 * 复制文件
	 * @param sourceFile
	 * @param targetFolder 目标文件夹
	 */
	public static void copyFile(File sourceFile, File targetFolder) {
		if(!targetFolder.exists())
			targetFolder.mkdirs();
		
		try(BufferedOutputStream writer=new BufferedOutputStream(new FileOutputStream(targetFolder.getAbsolutePath()+File.separatorChar+sourceFile.getName()))){
			copyFile(sourceFile, writer);
		} catch (IOException e) {
			logger.error("复制文件时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	
	/**
	 * 复制文件夹
	 * @param sourceFolder 
	 * @param targetFolder 目标文件夹
	 */
	public static void copyFolder(File sourceFolder, File targetFolder) {
		if(sourceFolder.isFile()) {
			copyFile(sourceFolder, targetFolder);
		} else {
			if(!targetFolder.exists())
				targetFolder.mkdirs();
			
			for (File sf : sourceFolder.listFiles()) {
				if(sf.isFile()) {
					copyFile(sf, targetFolder);
				} else {
					copyFolder(sf, new File(targetFolder.getAbsolutePath() + File.separatorChar + sf.getName()));
				}
			}
		}
	}
	
	/**
	 * 删除文件或文件夹
	 * @param files
	 */
	public static void deleteFiles(File... files) {
		for (File file : files) {
			if(file.isDirectory()) 
				deleteFiles(file.listFiles());
			file.delete();
		}
	}
	
	/**
	 * zip压缩文件
	 * @param folderName 所属文件名, 可为null, 表示最顶层的文件
	 * @param sourceFile
	 * @param out
	 * @throws IOException
	 */
	private static void zipFile(String folderName, File sourceFile, ZipOutputStream out) throws IOException {
		try(BufferedInputStream reader = new BufferedInputStream(new FileInputStream(sourceFile))){
			int len;
			byte[] b = new byte[1024];
			out.putNextEntry(new ZipEntry((folderName==null?sourceFile.getName():(folderName+File.separatorChar+sourceFile.getName()))));
			while((len = reader.read(b)) > 0) 
				out.write(b, 0, len);
			out.closeEntry();
		}
	}
	
	/**
	 * zip压缩文件
	 * @param sourceFile
	 * @param targetZipFile 目标压缩文件
	 */
	public static void zipFile(File sourceFile, File targetZipFile) {
		File folder = targetZipFile.getParentFile();
		if(!folder.exists())
			folder.mkdirs();
		
		try(ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetZipFile))){
			zipFile(null, sourceFile, out);
		} catch (IOException e) {
			logger.error("zip压缩文件时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	
	/**
	 * zip压缩文件夹
	 * @param sourceFolder
	 * @param targetZipFile 目标压缩文件
	 */
	public static void zipFolder(File sourceFolder, File targetZipFile) {
		if(sourceFolder.isFile()) {
			zipFile(sourceFolder, targetZipFile);
		}else{
			try(ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetZipFile))){
				for (File file : sourceFolder.listFiles()) {
					if(file.isFile()) {
						zipFile(null, file, out);
					}else {
						zipFolder(file.getName(), file, out);
					}
				}
			} catch (IOException e) {
				logger.error("zip压缩文件夹时出现异常: {}", ExceptionUtil.getStackTrace(e));
			}
		}
	}
	// 递归压缩文件夹
	private static void zipFolder(String sourceFolderName, File sourceFolder, ZipOutputStream out) throws IOException {
		for (File file : sourceFolder.listFiles()) {
			if(file.isFile()) {
				zipFile(sourceFolderName, file, out);
			}else {
				zipFolder(sourceFolderName + File.separatorChar + file.getName(), file, out);
			}
		}
	}
}
