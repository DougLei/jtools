package com.douglei.tools.instances.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.ExceptionUtil;

/**
 * 资源阅读器
 *指的是在resources目录下的文件
 * @author DougLei
 */
public class ResourcesReader extends Reader {
	private static final Logger logger = LoggerFactory.getLogger(ResourcesReader.class);
	
	private BufferedReader bufferedReader;
	
	public ResourcesReader(String projectConfigurationResourcePath) {
		super(projectConfigurationResourcePath);
		if(in != null) {
			bufferedReader = new BufferedReader(new InputStreamReader(in));
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
				logger.error("读取文件["+path+"]时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
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
