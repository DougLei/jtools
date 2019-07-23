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
	
	/**
	 * 
	 * @param name
	 * @param converter
	 * @return
	 */
	public static String convert(String name, Converter converter) {
		if(converter.getClass().isAnonymousClass()) {
			throw new UnsupportAnonymousNamingConverterException();
		}
		Converter converter_ = CONVERTERS.get(converter.getClass());
		if(converter_ == null) {
			converter_ = converter;
			CONVERTERS.put(converter.getClass(), converter_);
		}
		return converter_.doConvert(name);
	}
	
	/**
	 * 不经过缓存, 进行命名转换
	 * 可以使用匿名类
	 * @param name
	 * @param converter
	 * @return
	 */
	public static String convertNoCache(String name, Class<? extends Converter> converter) {
		return ((Converter) ConstructorUtil.newInstance(converter)).doConvert(name);
	}
	
	/**
	 * 不经过缓存, 进行命名转换
	 * 可以使用匿名类
	 * @param name
	 * @param converter
	 * @return
	 */
	public static String convertNoCache(String name, Converter converter) {
		return converter.doConvert(name);
	}
}
