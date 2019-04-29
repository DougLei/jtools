package com.douglei.utils.datatype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.utils.StringUtil;

/**
 * 数据类型转换工具类
 * @author DougLei
 */
public class ConvertUtil {
	private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);
	
	/**
	 * 简单的数据类型转换
	 * <pre>
	 * 	8大基本类型
	 *  String类型
	 * </pre>
	 * @param value
	 * @param targetClass
	 * @return
	 */
	public static Object simpleConvert(Object value, Class<?> targetClass) {
		if(value == null) {
			logger.debug("要进行数据类型转换的value值为空");
			return null;
		}
		logger.debug("convert value is {}, targetClass is {}", value, targetClass);
		
		String valueString = value.toString();
		if(targetClass == String.class) {
			logger.debug("targetClass is String.class");
			return valueString;
		}
		if(targetClass == char.class || targetClass == Character.class) {
			logger.debug("targetClass is Character.class");
			return valueString.charAt(0);
		}
		
		if(targetClass == short.class || targetClass == Short.class) {
			logger.debug("targetClass is Short.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为short类型时，源数据值value为空");
			}
			return Short.parseShort(valueString.trim());
		}
		if(targetClass == int.class || targetClass == Integer.class) {
			logger.debug("targetClass is Integer.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为integer类型时，源数据值value为空");
			}
			return Integer.parseInt(valueString.trim());
		}
		if(targetClass == long.class || targetClass == Long.class) {
			logger.debug("targetClass is Long.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为long类型时，源数据值value为空");
			}
			return Long.parseLong(valueString.trim());
		}
		
		if(targetClass == float.class || targetClass == Float.class) {
			logger.debug("targetClass is Float.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为float类型时，源数据值value为空");
			}
			return Float.parseFloat(valueString.trim());
		}
		if(targetClass == double.class || targetClass == Double.class) {
			logger.debug("targetClass is Double.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为double类型时，源数据值value为空");
			}
			return Double.parseDouble(valueString.trim());
		}
		
		if(targetClass == boolean.class || targetClass == Boolean.class) {
			logger.debug("targetClass is Boolean.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为boolean类型时，源数据值value为空");
			}
			return Boolean.parseBoolean(valueString.trim());
		}
		
		if(targetClass == byte.class || targetClass == Byte.class) {
			logger.debug("targetClass is Byte.class");
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为byte类型时，源数据值value为空");
			}
			return Byte.parseByte(valueString.trim());
		}
		throw new IllegalArgumentException("该方法只支持8大基本数据类型和String类型的转换操作");
	}
}
