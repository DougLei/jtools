package com.douglei.tools.utils.datatype;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类型工具类
 * @author DougLei
 */
public class DateTypeUtil {
	
	/**
	 * -格式，简单日期格式化类
	 * <p>日期格式为：yyyy-MM-dd</p>
	 */
	private static final SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * -格式，详细日期格式化类
	 * <p>日期格式为：yyyy-MM-dd HH:mm:ss</p>
	 */
	private static final SimpleDateFormat sdfDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 带时区的日期格式化类
	 * <p>UTC通用标准时，以Z来标识</p>
	 */
	private static final SimpleDateFormat sdfTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateString
	 * @return
	 */
	private static Date parseDate(String dateString){
		if(ValidationUtil.isDate(dateString)){
			try {
				if(dateString.endsWith("Z")){
					return sdfTimeZone.parse(dateString.replace("Z", " UTC"));
				}else if(dateString.indexOf(":") != -1){
					return sdfDetail.parse(dateString);
				}else if(ValidationUtil.isLimitLong(dateString)) {
					return new Date(Long.parseLong(dateString));
				}else{
					return sdfSimple.parse(dateString);
				}
			} catch (ParseException e) {
				throw new RuntimeException("格式化日期字符串["+dateString+"]为日期对象时, 出现异常", e);
			}
		}
		throw new IllegalArgumentException("日期字符串 ["+dateString+"] 的格式错误");
	}
	
	/**
	 * 格式化为java.util.Date类型的日期对象
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(Object date){
		if(date instanceof String) {
			return parseDate(date.toString());
		}else if(date instanceof Date) {
			return (Date)date;
		}else if(date instanceof java.sql.Date || date instanceof java.sql.Timestamp) {
			return new Date(((Date)date).getTime());
		}else if(date instanceof Long || date.getClass() == long.class) {
			return new Date((long)date);
		}
		throw new IllegalArgumentException("转换为java.util.Date时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
	
	
	/**
	 * 格式化为java.sql.Date类型的日期对象
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Object date){
		if(date instanceof String) {
			return new java.sql.Date(parseDate(date.toString()).getTime());
		}else if(date instanceof java.sql.Date) {
			return (java.sql.Date)date;
		}else if(date instanceof Date || date instanceof java.sql.Timestamp) {
			return new java.sql.Date(((Date)date).getTime());
		}else if(date instanceof Long || date.getClass() == long.class) {
			return new java.sql.Date((long)date);
		}
		throw new IllegalArgumentException("要转换为java.sql.Date时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
	
	/**
	 * 格式化为java.sql.Timestamp类型的日期对象
	 * @param dateStr
	 * @return
	 */
	public static Timestamp parseSqlTimestamp(Object date) {
		if(date instanceof String) {
			return new Timestamp(parseDate(date.toString()).getTime());
		}else if(date instanceof Timestamp) {
			return (Timestamp)date;
		}else if(date instanceof Date || date instanceof java.sql.Date) {
			return new Timestamp(((Date)date).getTime());
		}else if(date instanceof Long || date.getClass() == long.class) {
			return new Timestamp((long)date);
		}
		throw new IllegalArgumentException("要转换为java.sql.Timestamp时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}

	/**
	 * 判断是否是日期类型
	 * @param value
	 * @return
	 */
	public static boolean isDate(Object value) {
		if(value == null) {
			return false;
		}
		if(value instanceof Date || value instanceof java.sql.Date || value instanceof Timestamp) {
			return true;
		}
		return ValidationUtil.isDate(value.toString());
	}
}
