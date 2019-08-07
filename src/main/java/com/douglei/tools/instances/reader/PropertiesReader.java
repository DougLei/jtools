package com.douglei.tools.instances.reader;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.ExceptionUtil;
import com.douglei.tools.utils.StringUtil;

/**
 * properties资源阅读器
 * @author DougLei
 */
public class PropertiesReader extends Reader{
	private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
	
	private Properties properties;
	
	public PropertiesReader(String propertiesPath) {
		super(propertiesPath);
		setProperties();
	}
	
	private void setProperties() {
		if(ready()) {
			properties = new Properties();
			try {
				properties.load(in);
			} catch (IOException e) {
				logger.error("读取配置文件[{}]时出现异常: {}", path, ExceptionUtil.getExceptionDetailMessage(e));
			} finally {
				close();
			}
		}
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
}
