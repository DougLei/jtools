package com.douglei.utils.datatype;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * 数据类型验证工具类
 * @author StoneKing
 */
public class ValidationUtil {
	
	/**
	 * 是否是短整型
	 * @param value
	 * @return
	 */
	public static boolean isShort(Object value){
		if(value != null){
			if(value instanceof Short || value.getClass() == short.class){
				return true;
			}
			long l;
			if((l =isInteger_(value))!=-1) {
				return l>= Short.MIN_VALUE && l<= Short.MAX_VALUE;
			}
		}
		return false;
	}
	
	/**
	 * 是否是长整型
	 * @param value
	 * @return
	 */
	public static boolean isLong(Object value){
		if(value != null){
			if(value instanceof Long || value.getClass() == long.class){
				return true;
			}
			long l;
			if((l =isInteger_(value))!=-1) {
				return l>= Long.MIN_VALUE && l<= Long.MAX_VALUE;
			}
		}
		return false;
	}
	
	/**
	 * 是否是整型
	 * @param value
	 * @return
	 */
	public static boolean isInteger(Object value){
		if(value != null){
			if(value instanceof Integer || value.getClass() == int.class){
				return true;
			}
			long l;
			if((l =isInteger_(value))!=-1) {
				return l>= Integer.MIN_VALUE && l<= Integer.MAX_VALUE;
			}
		}
		return false;
	}
	
	private static long isInteger_(Object value) {
		String valueString = value.toString();
		if(integerTypePattern.matcher(valueString).matches()){
			return Long.parseLong(valueString);
		}
		return -1;
	}
	/** 判断整型格式的正则表达式 */
	private static final Pattern integerTypePattern = Pattern.compile("(\\+|-)?[0-9]+");
	
	/**
	 * 是否是浮点型
	 * @param value
	 * @return
	 */
	public static boolean isFloat(Object value){
		if(value != null){
			if(value instanceof Float || value.getClass() == float.class){
				return true;
			}
			return doubleTypePattern.matcher(value.toString()).matches();
		}
		return false;
	}
	
	/**
	 * 是否是浮点型
	 * @param value
	 * @return
	 */
	public static boolean isDouble(Object value){
		if(value != null){
			if(value instanceof Double || value.getClass() == double.class){
				return true;
			}
			return doubleTypePattern.matcher(value.toString()).matches();
		}
		return false;
	}
	
	/** 判断浮点型格式的正则表达式 */
	private static final Pattern doubleTypePattern = Pattern.compile("(\\+|-)?[0-9]+.[0-9]+");
	
	/**
	 * 是否是数字类型
	 * <p>整型/浮点型/短整型/长整型</p>
	 * @param value
	 * @return
	 */
	public static boolean isNumber(Object value){
		return isInteger(value) || isDouble(value) || isShort(value) || isLong(value) || isFloat(value);
	}
	
	/**
	 * 是否是boolean
	 * @param value
	 * @return
	 */
	public static boolean isBoolean(Object value){
		if(value != null){
			if(value instanceof Boolean || value.getClass() == boolean.class){
				return true;
			}
			
			String valueString = value.toString().toLowerCase();
			if("true".equals(valueString) || "false".equals(valueString)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否是date
	 * <p>字符串的日期格式，目前只支持yyyy-MM-dd HH:mm:ss格式</p>
	 * <p>java.util.Date</p>
	 * <p>java.sql.Date</p>
	 * <p>java.sql.Timestamp</p>
	 * @param value
	 * @return
	 */
	public static boolean isDate(Object value) {
		if(value != null){
			if(value instanceof Date || value instanceof java.sql.Date || value instanceof java.sql.Timestamp){
				return true;
			}
			
			String valueString = value.toString();
			if(dateTypePattern.matcher(valueString).matches()){
				return true;
			}
			if(timeZoneTypePattern.matcher(valueString).matches()){
				return true;
			}
			return false;
		}
		return false;
	}
	/** 判断日期格式的正则表达式 */
	private static final Pattern dateTypePattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])( ([0-9]|0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[0-5][0-9]):(0[0-9]|[0-5][0-9]))?");
	private static final Pattern timeZoneTypePattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])T([0-9]|0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[0-5][0-9]):(0[0-9]|[0-5][0-9]).[0-9][0-9][0-9]( )?Z");// 带时差的日期格式，例如：2018-09-08T06:53:35.000Z
	
	
	/**
	 * <pre>
	 * 	是否是基本的数据类型
	 * 	8大基本数据类型和String类型
	 * </pre>
	 * @param valueue
	 * @return
	 */
	public static boolean isBasicDataType(Object value) {
		if(value != null) {
			Class<?> valueClass = value.getClass();
			if((valueClass == String.class) || 
					(valueClass == int.class || valueClass == Integer.class) || 
					(valueClass == double.class || valueClass == Double.class) || 
					(valueClass == boolean.class || valueClass == Boolean.class) || 
					(valueClass == char.class || valueClass == Character.class) || 
					(valueClass == short.class || valueClass == Short.class) || 
					(valueClass == long.class || valueClass == Long.class) || 
					(valueClass == float.class || valueClass == Float.class) || 
					(valueClass == byte.class || valueClass == Byte.class)) {
				return true;
			}
		}
		return false;
	}
}
