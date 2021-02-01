package com.douglei.tools.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.douglei.tools.UtilRuntimeException;

/**
 * 
 * @author DougLei
 */
public class FileBufferedWriter implements AutoCloseable {
	private BufferedWriter writer;
	
	/**
	 * 
	 * @param targetFile
	 */
	public FileBufferedWriter(File targetFile) {
		this(targetFile, false, StandardCharsets.UTF_8);
	}
	
	/**
	 * 
	 * @param targetFile
	 * @param append
	 */
	public FileBufferedWriter(File targetFile, boolean append) {
		this(targetFile, append, StandardCharsets.UTF_8);
	}
	
	/**
	 * 
	 * @param targetFile
	 * @param charset
	 */
	public FileBufferedWriter(File targetFile, Charset charset) {
		this(targetFile, false, charset);
	}
	
	/**
	 * 
	 * @param targetFile
	 * @param append
	 * @param charset
	 */
	public FileBufferedWriter(File targetFile, boolean append, Charset charset) {
		try {
			if(!targetFile.exists()) {
				File folder = targetFile.getParentFile();
				if(!folder.exists()) 
					folder.mkdirs();
				targetFile.createNewFile();
			}
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile, append), charset));
		} catch (IOException e) {
			throw new UtilRuntimeException("创建实例时出现异常", e);
		}
	}
	
	public void newLine() {
		try {
			writer.newLine();
		} catch (IOException e) {
			throw new UtilRuntimeException("newLine时出现异常", e);
		}
	}
	public void write(char c) {
		try {
			writer.write(c);
		} catch (IOException e) {
			throw new UtilRuntimeException("write时出现异常", e);
		}
	}
	public void write(String str) {
		try {
			writer.write(str);
		} catch (IOException e) {
			throw new UtilRuntimeException("write时出现异常", e);
		}
	}
	public void writeln(String str) {
		try {
			writer.write(str);
			writer.newLine();
		} catch (IOException e) {
			throw new UtilRuntimeException("writeln时出现异常", e);
		}
	}
	
	@Override
	public void close() {
		try {
			writer.close();
			writer = null;
		} catch (IOException e) {
			throw new UtilRuntimeException("关闭时出现异常", e);
		}
	}
}
