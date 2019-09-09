package com.douglei.tools.instances.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.ExceptionUtil;
import com.douglei.tools.utils.StringUtil;

/**
 * properties资源阅读器
 * @author DougLei
 */
public class PropertiesReader extends Reader{
	private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
	
	private Properties properties;
	
	public PropertiesReader() {
	}
	public PropertiesReader(String propertiesPath) {
		super(propertiesPath);
	}
	public PropertiesReader(String propertiesPath, Charset charset) {
		super(propertiesPath, charset);
	}
	public PropertiesReader(InputStream in) {
		super(in);
	}
	public PropertiesReader(InputStream in, Charset charset) {
		super(in, charset);
	}
	
	@Override
	protected void initialSettings() {// setProperties
		if(in != null) {
			properties = new Properties();
			try {
				if(charset == null) {
					properties.load(in);
				}else {
					java.io.Reader reader = null;
					try {
						reader = new InputStreamReader(in, charset);
						properties.load(reader);
					} finally {
						CloseUtil.closeIO(reader);
					}
				}
			} catch (IOException e) {
				properties = null;
				logger.error("读取配置文件[{}]时出现异常: {}", path, ExceptionUtil.getExceptionDetailMessage(e));
			} finally {
				close();
			}
		}
	}
	
	@Override
	public boolean ready() {
		return properties != null && properties.size() > 0;
	}
	
	public String readProperty(String key) {
		return readProperty(key, null);
	}
	
	public String readProperty(String key, String defaultValue) {
		if(properties != null) {
			String v = properties.getProperty(key);
			if(StringUtil.notEmpty(v)) {
				return v;
			}
		}
		return defaultValue;
	}
	
	public Set<Entry<Object, Object>> entrySet() {
		return properties.entrySet();
	}
}
