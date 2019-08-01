package com.douglei.tools.utils.naming.converter;

import java.util.HashMap;
import java.util.Map;

import com.douglei.tools.utils.reflect.ConstructorUtil;

/**
 * 命名转换工具类
 * @author DougLei
 */
public class ConverterUtil {
	private static final Map<Class<?>, Converter> CONVERTERS = new HashMap<Class<?>, Converter>(4);
	
	/**
	 * 
	 * @param name
	 * @param converterClass
	 * @return
	 */
	public static String convert(String name, Class<? extends Converter> converter) {
		Converter converter_ = CONVERTERS.get(converter);
		if(converter_ == null) {
			converter_ = (Converter) ConstructorUtil.newInstance(converter);
			CONVERTERS.put(converter, converter_);
		}
		return converter_.doConvert(name);
	}
}
