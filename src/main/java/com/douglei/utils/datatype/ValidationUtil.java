package com.douglei.utils.datatype;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据类型验证工具类
 * @author StoneKing
 */
public class ValidationUtil {
	private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class);
	
	/**
	 * 是否是整型
	 * @param val
	 * @return
	 */
	public static boolean isInteger(Object val){
		logger.debug("判断是否是integer类型，传入的参数值为: {}", val);
		if(val != null){
			if(val instanceof Integer){
				return true;
			}
			
			String valStr = val.toString().trim();
			logger.debug("将object转换为字符串的值为[{}]", valStr);
			if(integerTypePattern.matcher(valStr).matches()){
				return true;
			}
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
	public static boolean isFloat(Object val){
		logger.debug("判断是否是浮点类型(java.lang.Float)(java.lang.Double)(java.math.BigDecimal)，传入的参数值为: {}", val);
		if(val != null){
			if(val instanceof Float || val instanceof Double || val instanceof BigDecimal){
				return true;
			}
			
			String valStr = val.toString().trim();
			logger.debug("将object转换为字符串的值为[{}]", valStr);
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
	 * <p>整型/浮点型</p>
	 * @param val
	 * @return
	 */
	public static boolean isNumber(Object val){
		logger.debug("判断是否是数字类型，传入的参数值为: {}", val);
		return isInteger(val) || isFloat(val);
	}
	
	/**
	 * 是否是boolean
	 * @param val
	 * @return
	 */
	public static boolean isBoolean(Object val){
		logger.debug("判断是否是boolean类型，传入的参数值为: {}", val);
		if(val != null){
			if(val instanceof Boolean){
				return true;
			}
			
			String valStr = val.toString().trim();
			logger.debug("将object转换为字符串的值为[{}]", valStr);
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
		logger.debug("判断是否是浮点类型(java.util.Date)(java.sql.Date)(java.sql.Timestamp)，传入的参数值为: {}", val);
		if(val != null){
			if(val instanceof Date || val instanceof java.sql.Date || val instanceof java.sql.Timestamp){
				return true;
			}
			
			String valStr = val.toString().trim();
			logger.debug("将object转换为字符串的值为[{}]", valStr);
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
}
