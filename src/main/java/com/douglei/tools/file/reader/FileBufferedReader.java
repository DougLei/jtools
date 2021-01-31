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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public class FileBufferedReader implements AutoCloseable{
	private static final Logger logger = LoggerFactory.getLogger(FileBufferedReader.class);
	private BufferedReader reader;
	
	/**
	 * 读取classpath下的文件内容
	 * @param name
	 */
	public FileBufferedReader(String name) {
		this.reader = new BufferedReader(
				new InputStreamReader(FileBufferedReader.class.getClassLoader().getResourceAsStream(name), StandardCharsets.UTF_8));
	}
	/**
	 * 读取classpath下的文件内容
	 * @param name
	 * @param charset
	 */
	public FileBufferedReader(String name, Charset charset) {
		this.reader = new BufferedReader(
				new InputStreamReader(FileBufferedReader.class.getClassLoader().getResourceAsStream(name), charset));
	}
	
	/**
	 * 
	 * @param file
	 */
	public FileBufferedReader(File file) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			logger.error("创建实例时出现异常: {}", ExceptionUtil.getStackTrace(e));
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
			logger.error("创建实例时出现异常: {}", ExceptionUtil.getStackTrace(e));
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
	 * 是否可以读取
	 * @return
	 */
	public boolean ready() {
		try {
			return reader != null && reader.ready();
		} catch (IOException e) {
			
		}
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
		if(ready()) {
			StringBuilder tmt = new StringBuilder(capacity);
			try {
				while(reader.ready()) 
					tmt.append(reader.readLine());
				return tmt.toString();
			} catch (IOException e) {
				logger.error("读取文件时出现异常: {}", ExceptionUtil.getStackTrace(e));
			}
		}
		return null;
	}
	
	
	/**
	 * 读取一行数据
	 * @return
	 */
	public String readLine() {
		if(ready()) {
			try {
				if(reader.ready()) 
					return reader.readLine();
			} catch (IOException e) {
				logger.error("读取文件时出现异常: {}", ExceptionUtil.getStackTrace(e));
			}
		}
		return null;
	}

	@Override
	public void close() {
		try {
			reader.close();
			reader = null;
		} catch (IOException e) {
			logger.error("关闭时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
}
