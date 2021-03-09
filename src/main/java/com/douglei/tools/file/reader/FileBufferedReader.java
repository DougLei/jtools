package com.douglei.tools.file.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.douglei.tools.UtilRuntimeException;

/**
 * 
 * @author DougLei
 */
public class FileBufferedReader implements AutoCloseable{
	private BufferedReader reader;
	
	/**
	 * 读取classpath下的文件内容(基于java resource)
	 * @param filepath
	 */
	public FileBufferedReader(String filepath) {
		this.reader = new BufferedReader(
				new InputStreamReader(FileBufferedReader.class.getClassLoader().getResourceAsStream(filepath), StandardCharsets.UTF_8));
	}
	/**
	 * 读取classpath下的文件内容(基于java resource)
	 * @param filepath
	 * @param charset
	 */
	public FileBufferedReader(String filepath, Charset charset) {
		this.reader = new BufferedReader(
				new InputStreamReader(FileBufferedReader.class.getClassLoader().getResourceAsStream(filepath), charset));
	}
	
	/**
	 * 
	 * @param file
	 */
	public FileBufferedReader(File file) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			throw new UtilRuntimeException("创建实例时出现异常", e);
		}
	}
	/**
	 * 
	 * @param file
	 * @param charset
	 */
	public FileBufferedReader(File file, Charset charset) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		} catch (FileNotFoundException e) {
			throw new UtilRuntimeException("创建实例时出现异常", e);
		}
	}
	
	/**
	 * 
	 * @param in
	 */
	public FileBufferedReader(InputStream in) {
		this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
	}
	/**
	 * 
	 * @param in
	 * @param charset
	 */
	public FileBufferedReader(InputStream in, Charset charset) {
		this.reader = new BufferedReader(new InputStreamReader(in, charset));
	}
	
	/**
	 * 读取配置文件中的所有内容
	 * @return
	 */
	public String readAll() {
		return readAll(16);
	}
	
	/**
	 * 读取配置文件中的所有内容
	 * @param capacity 存储读取内容容器的初始容量
	 * @return
	 */
	public String readAll(int capacity) {
		try {
			StringBuilder sb = new StringBuilder(capacity);
			while(reader.ready()) 
				sb.append(reader.readLine());
			return sb.toString();
		} catch (IOException e) {
			throw new UtilRuntimeException("读取文件(readAll)时出现异常", e);
		}
	}
	
	
	/**
	 * 读取一行数据
	 * @return
	 */
	public String readLine() {
		try {
			if(reader.ready()) 
				return reader.readLine();
			return null;
		} catch (IOException e) {
			throw new UtilRuntimeException("读取文件(readLine)时出现异常", e);
		}
	}

	@Override
	public void close() {
		try {
			reader.close();
			reader = null;
		} catch (IOException e) {
			throw new UtilRuntimeException("关闭时出现异常", e);
		}
	}
}
