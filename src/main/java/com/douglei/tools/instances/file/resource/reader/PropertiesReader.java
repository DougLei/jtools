package com.douglei.tools.instances.file.resource.reader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.douglei.tools.utils.StringUtil;

/**
 * properties资源阅读器
 * @author DougLei
 */
public class PropertiesReader extends AbstractPropertiesReader{
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
		properties= loadProperties();
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
