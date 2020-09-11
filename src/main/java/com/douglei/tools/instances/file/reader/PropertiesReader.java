package com.douglei.tools.instances.file.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.StringUtil;

/**
 * properties资源阅读器
 * @author DougLei
 */
public class PropertiesReader {
	private Properties properties;
	private boolean ready;
	
	/**
	 * 读取classpath下的properties文件
	 * @param name
	 */
	public PropertiesReader(String name) {
		this(name, StandardCharsets.UTF_8);
	}
	/**
	 * 读取classpath下的properties文件
	 * @param name
	 * @param charset
	 */
	public PropertiesReader(String name, Charset charset) {
		this(PropertiesReader.class.getClassLoader().getResourceAsStream(name), charset);
	}
	
	/**
	 * 读取磁盘路径下的properties文件
	 * @param file
	 * @throws FileNotFoundException 
	 */
	public PropertiesReader(File file) throws FileNotFoundException {
		this(file, StandardCharsets.UTF_8);
	}
	/**
	 * 读取磁盘路径下的properties文件
	 * @param file
	 * @param charset
	 * @throws FileNotFoundException 
	 */
	public PropertiesReader(File file, Charset charset) throws FileNotFoundException {
		this(new FileInputStream(file), charset);
	}
	
	/**
	 * 读取输入流中的properties文件
	 * @param in
	 */
	public PropertiesReader(InputStream in) {
		this(in, StandardCharsets.UTF_8);
	}
	/**
	 * 读取输入流中的properties文件
	 * @param in
	 * @param charset
	 */
	public PropertiesReader(InputStream in, Charset charset) {
		if(in != null) {
			Reader reader = null;
			try {
				reader = new InputStreamReader(in, charset);
				this.properties = new Properties();
				this.properties.load(reader);
				this.ready = properties != null && !properties.isEmpty();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				CloseUtil.closeIO(reader);
			}
		}
	}
	
	/**
	 * 是否可以读取
	 * @return
	 */
	public boolean ready() {
		return ready;
	}
	
	/**
	 * 获取指定key的值
	 * @param key
	 * @return
	 */
	public String readProperty(String key) {
		if(ready())
			return readProperty(key, null);
		return null;
	}
	
	/**
	 * 获取指定key的值
	 * @param key
	 * @param defaultValue 如果获取的值为null, 返回该默认值
	 * @return
	 */
	public String readProperty(String key, String defaultValue) {
		if(ready()) {
			String v = properties.getProperty(key);
			if(StringUtil.notEmpty(v)) {
				return v;
			}
		}
		return defaultValue;
	}
	
	/**
	 * 获取键值对集合
	 * @return
	 */
	public Set<Entry<Object, Object>> entrySet() {
		if(ready())
			return properties.entrySet();
		return Collections.emptySet();
	}
}
