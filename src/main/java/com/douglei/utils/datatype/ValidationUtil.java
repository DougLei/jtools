package com.douglei.utils.datatype;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 数据类型验证工具类
 * @author StoneKing
 */
public class ValidationUtil {
	
	/**
	 * 是否是短整型
	 * @param val
	 * @return
	 */
	public static boolean isShort(Object val){
		if(val != null){
			if(val instanceof Short || val.getClass() == short.class){
				return true;
			}
			return isInteger_(val);
		}
		return false;
	}
	
	/**
	 * 是否是长整型
	 * @param val
	 * @return
	 */
	public static boolean isLong(Object val){
		if(val != null){
			if(val instanceof Long || val.getClass() == long.class){
				return true;
			}
			return isInteger_(val);
		}
		return false;
	}
	
	/**
	 * 是否是整型
	 * @param val
	 * @return
	 */
	public static boolean isInteger(Object val){
		if(val != null){
			if(val instanceof Integer || val.getClass() == int.class){
				return true;
			}
			return isInteger_(val);
		}
		return false;
	}
	
	private static boolean isInteger_(Object val) {
		String valStr = val.toString();
		if(integerTypePattern.matcher(valStr).matches()){
			return true;
		}
		return false;
	}
	/** 判断整型格式的正则表达式 */
	private static final Pattern integerTypePattern = Pattern.compile("(\\+|-)?[0-9]+");
	
	/**
	 * 是否是浮点型
	 * <p>java.lang.Float</p>
	 * <p>java.lang.Double</p>
	 * <p>java.math.BigDecimal</p>
	 * @param val
	 * @return
	 */
	public static boolean isDouble(Object val){
		if(val != null){
			Class<?> valClass = val.getClass();
			if(val instanceof Float || valClass == float.class || val instanceof Double || valClass == double.class || val instanceof BigDecimal){
				return true;
			}
			
			String valStr = val.toString();
			if(doubleTypePattern.matcher(valStr).matches()){
				return true;
			}
		}
		return false;
	}
	/** 判断浮点型格式的正则表达式 */
	private static final Pattern doubleTypePattern = Pattern.compile("(\\+|-)?[0-9]+.[0-9]+");
	
	/**
	 * 是否是数字类型
	 * <p>整型/浮点型/短整型/长整型</p>
	 * @param val
	 * @return
	 */
	public static boolean isNumber(Object val){
		return isInteger(val) || isDouble(val) || isShort(val) || isLong(val);
	}
	
	/**
	 * 是否是boolean
	 * @param val
	 * @return
	 */
	public static boolean isBoolean(Object val){
		if(val != null){
			if(val instanceof Boolean || val.getClass() == boolean.class){
				return true;
			}
			
			String valStr = val.toString().toLowerCase();
			if("true".equals(valStr) || "false".equals(valStr)){
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
	 * @param val
	 * @return
	 */
	public static boolean isDate(Object val) {
		if(val != null){
			if(val instanceof Date || val instanceof java.sql.Date || val instanceof java.sql.Timestamp){
				return true;
			}
			
			String valStr = val.toString();
			if(dateTypePattern.matcher(valStr).matches()){
				return true;
			}
			if(timeZoneTypePattern.matcher(valStr).matches()){
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
	 * @param value
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
