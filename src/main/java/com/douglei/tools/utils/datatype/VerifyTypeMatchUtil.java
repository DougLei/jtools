package com.douglei.tools.utils.datatype;

import java.util.regex.Pattern;

/**
 * 验证类型是否匹配的工具类
 * @author StoneKing
 */
public class VerifyTypeMatchUtil {
	
	// -----------------------------------------------------------------------------------------------------------------
	// 整型
	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * <pre>
	 * 	是否是字节
	 * 	限定在byte的长度范围
	 * </pre>
	 * @param value
	 * @return
	 */
	public static boolean isLimitByte(String value){
		if(value != null){
			Long l;
			if((l =isInteger_(value))!=null) {
				return l>= Byte.MIN_VALUE && l<= Byte.MAX_VALUE;
			}
		}
		return false;
	}
	
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
			Long l;
			if((l =isInteger_(value))!=null) {
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
			Long l;
			if((l =isInteger_(value))!=null) {
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
	public static boolean isLimitLong(String value){
		if(value != null){
			Long l;
			if((l =isInteger_(value))!=null) {
				return l>= Long.MIN_VALUE && l<= Long.MAX_VALUE;
			}
		}
		return false;
	}
	
	// 是否是整数, 并返回转换为long类型的值, 如果返回-1则证明不是整数
	private static Long isInteger_(String value) {
		if(integerTypePattern.matcher(value).matches()){
			return Long.parseLong(value);
		}
		return null;
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
	 * <pre>
	 * 	是否是浮点型
	 * 	限定在必须有小数
	 * </pre>
	 * @param value
	 * @return
	 */
	public static boolean isLimitDouble(String value){
		if(value != null){
			return doubleTypePattern.matcher(value).matches();
		}
		return false;
	}
	/** 判断浮点型格式的正则表达式 */
	private static final Pattern doubleTypePattern = Pattern.compile("(\\+|-)?[0-9]+.[0-9]+");
	
	/**
	 * 是否是浮点型
	 * @param value
	 * @return
	 */
	public static boolean isDouble(String value){
		return isLimitDouble(value) || isInteger(value);
	}
	
	/**
	 * 是否是数字类型
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value){
		return isDouble(value);
	}
	
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
}
