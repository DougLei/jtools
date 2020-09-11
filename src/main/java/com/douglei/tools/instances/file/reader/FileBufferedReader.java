package com.douglei.tools.instances.file.reader;

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

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public class FileBufferedReader {
	private static final Logger logger = LoggerFactory.getLogger(FileBufferedReader.class);
	private BufferedReader bufferedReader;
	
	/**
	 * 读取classpath下的文件内容
	 * @param name
	 */
	public FileBufferedReader(String name) {
		this(name, StandardCharsets.UTF_8);
	}
	/**
	 * 读取classpath下的文件内容
	 * @param name
	 * @param charset
	 */
	public FileBufferedReader(String name, Charset charset) {
		this(FileBufferedReader.class.getClassLoader().getResourceAsStream(name), charset);
	}
	
	/**
	 * 读取磁盘路径下的文件内容
	 * @param file
	 * @throws FileNotFoundException 
	 */
	public FileBufferedReader(File file) throws FileNotFoundException {
		this(file, StandardCharsets.UTF_8);
	}
	/**
	 * 读取磁盘路径下的文件内容
	 * @param file
	 * @param charset
	 * @throws FileNotFoundException
	 */
	public FileBufferedReader(File file, Charset charset) throws FileNotFoundException {
		this(new FileInputStream(file), charset);
	}
	
	/**
	 * 读取输入流中的内容
	 * @param in
	 */
	public FileBufferedReader(InputStream in) {
		this(in, StandardCharsets.UTF_8);
	}
	/**
	 * 读取输入流中的内容
	 * @param in
	 * @param charset
	 */
	public FileBufferedReader(InputStream in, Charset charset) {
		if(in != null)
			this.bufferedReader = new BufferedReader(new InputStreamReader(in, charset));
	}
	
	/**
	 * 是否可以读取
	 * @return
	 */
	public boolean ready() {
		return bufferedReader != null;
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
				while(bufferedReader.ready()) 
					tmt.append(bufferedReader.readLine());
				return tmt.toString();
			} catch (IOException e) {
				logger.error("读取文件时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
			} finally {
				close();
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
				if(bufferedReader.ready()) 
					return bufferedReader.readLine();
			} catch (IOException e) {
				logger.error("读取文件时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
			}
			close();
		}
		return null;
	}

	/**
	 * 关闭, 如果是读取完了所有内容, 调用方是不需要手动关闭的, 该程序会自动关闭
	 */
	public void close() {
		if(bufferedReader != null) {
			CloseUtil.closeIO(bufferedReader);
			bufferedReader = null;
		}
	}
}
