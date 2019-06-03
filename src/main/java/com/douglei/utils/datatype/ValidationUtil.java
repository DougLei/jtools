package com.douglei.utils.datatype;

import java.util.regex.Pattern;

/**
 * 数据类型验证工具类
 * @author StoneKing
 */
public class ValidationUtil {
	
	// -----------------------------------------------------------------------------------------------------------------
	// 整型
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * <pre>
	 * 	是否是短整型
	 * 	限定在short的长度范围
	 * </pre>
	 * @param value
	 * @return
	 */
	public static boolean isLimitShort(String value){
		if(value != null){
			long l;
			if((l =isLimitInteger_(value))!=-1) {
				return l>= Short.MIN_VALUE && l<= Short.MAX_VALUE;
			}
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 	是否是整型
	 * 	限定在int的长度范围
	 * </pre>
	 * @param value
	 * @return
	 */
	public static boolean isLimitInteger(String value){
		if(value != null){
			long l;
			if((l =isLimitInteger_(value))!=-1) {
				return l>= Integer.MIN_VALUE && l<= Integer.MAX_VALUE;
			}
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 	是否是长整型
	 * 	限定在long的长度范围
	 * </pre>
	 * @param value
	 * @return
	 */
	public static boolean isLong(String value){
		if(value != null){
			long l;
			if((l =isLimitInteger_(value))!=-1) {
				return l>= Long.MIN_VALUE && l<= Long.MAX_VALUE;
			}
		}
		return false;
	}
	
	// 是否是限制整型
	private static long isLimitInteger_(String value) {
		if(integerTypePattern.matcher(value).matches()){
			return Long.parseLong(value);
		}
		return -1;
	}
	
	/**
	 * (只)判断是否是整型, 
	 * 不判断整型的长度, 
	 * 不去区分short/int/long
	 * @param value
	 * @return
	 */
	public static boolean isInteger(String value) {
		if(value != null){
			return integerTypePattern.matcher(value).matches();
		}
		return false;
	}
	
	/** 判断整型格式的正则表达式 */
	private static final Pattern integerTypePattern = Pattern.compile("(\\+|-)?[0-9]+");
	
	// -----------------------------------------------------------------------------------------------------------------
	// 浮点型
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * 是否是浮点型
	 * @param value
	 * @return
	 */
	public static boolean isDouble(String value){
		if(value != null){
			return doubleTypePattern.matcher(value).matches();
		}
		return false;
	}
	/** 判断浮点型格式的正则表达式 */
	private static final Pattern doubleTypePattern = Pattern.compile("(\\+|-)?[0-9]+.[0-9]+");
	
	// -----------------------------------------------------------------------------------------------------------------
	// 布尔型
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * 是否是boolean
	 * @param value
	 * @return
	 */
	public static boolean isBoolean(String value){
		if(value != null){
			return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
		}
		return false;
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	// 日期型
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * 是否是date
	 * <p>字符串的日期格式，目前只支持yyyy-MM-dd HH:mm:ss格式</p>
	 * @param value
	 * @return
	 */
	public static boolean isDate(String value) {
		if(value != null){
			if(dateTypePattern.matcher(value).matches()){
				return true;
			}
			return timeZoneTypePattern.matcher(value).matches();
		}
		return false;
	}
	/** 判断日期格式的正则表达式 */
	private static final Pattern dateTypePattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])( ([0-9]|0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[0-5][0-9]):(0[0-9]|[0-5][0-9]))?");
	private static final Pattern timeZoneTypePattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])T([0-9]|0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[0-5][0-9]):(0[0-9]|[0-5][0-9]).[0-9][0-9][0-9]( )?Z");// 带时差的日期格式，例如：2018-09-08T06:53:35.000Z
	
	// -----------------------------------------------------------------------------------------------------------------
	// 基本类型
	// -----------------------------------------------------------------------------------------------------------------
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
	
	/**
	 * 是否是数字类型
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value){
		return isInteger(value) || isDouble(value);
	}
}
