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
	public static String convert(String name, Class<? extends Converter> converterClass) {
		Converter converter = CONVERTERS.get(converterClass);
		if(converter == null) {
			converter = (Converter) ConstructorUtil.newInstance(converterClass);
			CONVERTERS.put(converterClass, converter);
		}
		return converter.doConvert(name);
	}
	
	/**
	 * 
	 * @param name
	 * @param converterClass
	 * @return
	 */
	public static String convert(String name, Converter converter) {
		Converter converter_ = CONVERTERS.get(converter.getClass());
		if(converter_ == null) {
			converter_ = converter;
			CONVERTERS.put(converter.getClass(), converter);
		}
		return converter_.doConvert(name);
	}
}
