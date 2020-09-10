package com.douglei.tools.instances.file.resource.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.ExceptionUtil;

/**
 * 资源阅读器
 * @author DougLei
 */
public class ResourceReader extends AbstractResourcesReader {
	private static final Logger logger = LoggerFactory.getLogger(ResourceReader.class);
	
	private BufferedReader bufferedReader;
	
	public ResourceReader() {
	}
	public ResourceReader(String projectConfigurationResourcePath) {
		super(projectConfigurationResourcePath);
	}
	public ResourceReader(String projectConfigurationResourcePath, Charset charset) {
		super(projectConfigurationResourcePath, charset);
	}
	public ResourceReader(InputStream in) {
		super(in);
	}
	public ResourceReader(InputStream in, Charset charset) {
		super(in, charset);
	}
	
	@Override
	protected void initialSettings() {// setBufferedReader
		if(in != null) {
			bufferedReader = new BufferedReader(charset==null?new InputStreamReader(in):new InputStreamReader(in, charset));
		}
	}
	
	@Override
	public boolean ready() {
		return bufferedReader != null;
	}
	/**
	 * 读取配置文件中的所有内容
	 * 读取完成后, 该方法会关闭阅读器
	 * @return
	 */
	public StringBuilder readAll() {
		return readAll(16);
	}
	
	/**
	 * 读取配置文件中的所有内容
	 * 读取完成后, 该方法会关闭阅读器
	 * @param capacity StringBuilder的初始容量
	 * @return
	 */
	public StringBuilder readAll(int capacity) {
		if(ready()) {
			StringBuilder tmt = new StringBuilder(capacity);
			try {
				while(bufferedReader.ready()) {
					tmt.append(bufferedReader.readLine().trim());
				}
				return tmt;
			} catch (IOException e) {
				logger.error("读取文件["+path+"]时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
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
				if(bufferedReader.ready()) {
					return bufferedReader.readLine().trim();
				}
			} catch (IOException e) {
				logger.error("读取资源文件时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
			}
			close();
		}
		return null;
	}

	@Override
	protected void close() {
		if(bufferedReader != null) {
			CloseUtil.closeIO(bufferedReader);
			bufferedReader = null;
		}
	}
}
