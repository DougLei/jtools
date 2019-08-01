package com.douglei.tools.utils.naming.converter;

import java.util.HashMap;
import java.util.Map;

import com.douglei.tools.utils.reflect.ConstructorUtil;

/**
 * 命名转换工具类
 * @author DougLei
 */
public class ConverterUtil {
	private static final Map<Class<?>, Converter> converters = new HashMap<Class<?>, Converter>(4);
	
	/**
	 * 
	 * @param originName
	 * @param converterClass
	 * @return
	 */
	public static String convert(String originName, Class<? extends Converter> converter) {
		Converter converter_ = converters.get(converter);
		if(converter_ == null) {
			converter_ = (Converter) ConstructorUtil.newInstance(converter);
			converters.put(converter, converter_);
		}
		return converter_.doConvert(originName);
	}
	
	/**
	 * 
	 * @param originName
	 * @param converter
	 * @return
	 */
	public static String convert(String originName, Converter converter) {
		return converter.doConvert(originName);
	}
}
