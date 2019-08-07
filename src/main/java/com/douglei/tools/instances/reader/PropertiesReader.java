package com.douglei.tools.instances.reader;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties资源阅读器
 * @author DougLei
 */
public class PropertiesReader extends Reader{
	private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);
	
	private Properties properties;
	
	public PropertiesReader(String propertiesPath) {
		super(propertiesPath);
		if(ready()) {
			properties = new Properties();
			properties.load(in);
		}
	}
	
	protected boolean ready() {
		return in != null;
	}
	
	
	
	public String readProperty(String key) {
		return readProperty(key, null);
	}
	
	public String readProperty(String key, String defaultValue) {
		if(properties == null && in != null) {
			
		}
		return null;
	}
}
