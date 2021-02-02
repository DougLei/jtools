package com.douglei.tools.file.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.douglei.tools.UtilRuntimeException;

/**
 * properties资源阅读器
 * @author DougLei
 */
public class PropertiesReader {
	private Properties properties;
	
	/**
	 * 读取classpath下的properties文件
	 * @param name
	 */
	public PropertiesReader(String name) {
		initial(PropertiesReader.class.getClassLoader().getResourceAsStream(name), StandardCharsets.UTF_8);
	}
	/**
	 * 读取classpath下的properties文件
	 * @param name
	 * @param charset
	 */
	public PropertiesReader(String name, Charset charset) {
		initial(PropertiesReader.class.getClassLoader().getResourceAsStream(name), charset);
	}
	
	/**
	 * 
	 * @param file
	 */
	public PropertiesReader(File file) {
		try {
			initial(new FileInputStream(file), StandardCharsets.UTF_8);
		} catch (FileNotFoundException e) {
			throw new UtilRuntimeException("创建实例时出现异常", e);
		}
	}
	/**
	 * 
	 * @param file
	 * @param charset
	 */
	public PropertiesReader(File file, Charset charset) {
		try {
			initial(new FileInputStream(file), charset);
		} catch (FileNotFoundException e) {
			throw new UtilRuntimeException("创建实例时出现异常", e);
		}
	}
	
	/**
	 * 
	 * @param in
	 */
	public PropertiesReader(InputStream in) {
		initial(in, StandardCharsets.UTF_8);
	}
	/**
	 * 
	 * @param in
	 * @param charset
	 */
	public PropertiesReader(InputStream in, Charset charset) {
		initial(in, charset);
	}
	
	// 初始化实例
	private void initial(InputStream in, Charset charset) {
		try (Reader reader = new InputStreamReader(in, charset)){
			this.properties = new Properties();
			this.properties.load(reader);
		} catch (IOException e) {
			throw new UtilRuntimeException("创建实例时出现异常", e);
		}
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return properties.isEmpty();
	}
	
	/**
	 * 获取指定key的值
	 * @param key
	 * @return
	 */
	public String readProperty(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 获取指定key的值
	 * @param key
	 * @param defaultValue 如果获取的值为null, 返回该默认值
	 * @return
	 */
	public String readProperty(String key, String defaultValue) {
		String value = properties.getProperty(key);
		if(value == null) 
			return defaultValue;
		return value;
	}
	
	/**
	 * 获取键值对集合
	 * @return
	 */
	public Set<Entry<Object, Object>> entrySet() {
		return properties.entrySet();
	}
}
