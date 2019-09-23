package com.douglei.tools.instances.file.resources.reader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.douglei.tools.utils.naming.converter.ConverterUtil;
import com.douglei.tools.utils.naming.converter.impl.PropertiesConfigurationKey2PropertyNameConverter;
import com.douglei.tools.utils.reflect.IntrospectorUtil;

/**
 * properties 配置资源阅读器
 * @author DougLei
 */
public class PropertiesConfigurationBeanReader extends AbstractPropertiesReader{
	private Object propertiesConfigurationBean;
	
	public PropertiesConfigurationBeanReader(String propertiesPath, Object propertiesConfigurationBean) {
		super(propertiesPath);
		this.propertiesConfigurationBean = propertiesConfigurationBean;
	}
	public PropertiesConfigurationBeanReader(String propertiesPath, Object propertiesConfigurationBean, Charset charset) {
		super(propertiesPath, charset);
		this.propertiesConfigurationBean = propertiesConfigurationBean;
	}
	public PropertiesConfigurationBeanReader(InputStream in, Object propertiesConfigurationBean) {
		super(in);
		this.propertiesConfigurationBean = propertiesConfigurationBean;
	}
	public PropertiesConfigurationBeanReader(InputStream in, Object propertiesConfigurationBean, Charset charset) {
		super(in, charset);
		this.propertiesConfigurationBean = propertiesConfigurationBean;
	}
	
	@Override
	protected void initialSettings() {
		Properties properties = loadProperties();
		if(properties != null) {
			if(properties.size() > 0) {
				final Map<String, Object> propertyMap = new HashMap<String, Object>(16);
				Set<Entry<Object, Object>> kvs = properties.entrySet();
				kvs.forEach(k -> {
					if(k.getValue() != null) {
						propertyMap.put(ConverterUtil.convert(k.getKey().toString(), PropertiesConfigurationKey2PropertyNameConverter.class), k.getValue());
					}
				});
				properties.clear();
				
				if(propertyMap.size() > 0) {
					IntrospectorUtil.setProperyValues(propertiesConfigurationBean, propertyMap);
					propertyMap.clear();
				}
			}
			properties = null;
		}
	}
	
	@Override
	public boolean ready() {
		return false;
	}
}
