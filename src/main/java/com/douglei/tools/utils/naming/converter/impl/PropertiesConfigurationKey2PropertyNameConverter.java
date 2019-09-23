package com.douglei.tools.utils.naming.converter.impl;

import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.naming.converter.Converter;

/**
 * 将properties配置文件中的key转换为属性名的转换器
 * user.name => userName
 * @author DougLei
 */
public class PropertiesConfigurationKey2PropertyNameConverter implements Converter {
	
	@Override
	public String doConvert(String propertiesKey) {
		StringBuilder sb = new StringBuilder(propertiesKey.length());
		propertiesKey = StringUtil.trim(propertiesKey, '.');
		
		char c;
		for (short i = 0; i < propertiesKey.length(); i++) {
			c = propertiesKey.charAt(i);
			if(c == '.') {
				c = propertiesKey.charAt(++i);
				if(c > 96 && c < 123) {
					c = (char)(c-32);
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
