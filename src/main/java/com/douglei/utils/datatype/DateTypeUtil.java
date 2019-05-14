package com.douglei.utils.datatype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期类型工具类
 * @author DougLei
 */
public class DateTypeUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateTypeUtil.class);
	
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
	 * 转换为yyyy-MM-dd格式的字符串
	 * @param date
	 * @return
	 */
	public static String simpleFormat(Date date) {
		return sdfSimple.format(date);
	}
	
	/**
	 * 转换为yyyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date
	 * @return
	 */
	public static String detailFormat(Date date) {
		return sdfDetail.format(date);
	}
	
	/**
	 * 转换为UTC通用标准时，以Z来标识格式的字符串
	 * @param date
	 * @return
	 */
	public static String timeZoneFormat(Date date) {
		return sdfDetail.format(date);
	}
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString){
		logger.debug("将字符串[{}]格式化为日期对象", dateString);
		if(!ValidationUtil.isDate(dateString)){
			logger.debug("日期字符串 [{}] 的格式错误", dateString);
			throw new IllegalArgumentException("日期字符串 ["+dateString+"] 的格式错误");
		}
		try {
			dateString = dateString.trim();
			if(dateString.endsWith("Z")){
				return sdfTimeZone.parse(dateString.replace("Z", " UTC"));
			}else if(dateString.indexOf(":") != -1){
				return sdfDetail.parse(dateString);
			}else{
				return sdfSimple.parse(dateString);
			}
		} catch (ParseException e) {
			throw new RuntimeException("格式化日期字符串["+dateString+"]为日期对象时, 出现异常", e);
		}
	}
	
	
	
	/**
	 * 格式化为java.util.Date类型的日期对象
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(Object date){
		if(logger.isDebugEnabled()) {
			logger.debug("将实例[{}], 值为[{}] , 格式化为java.util.Date类型的日期对象", date.getClass(), date);
		}
		if(date instanceof String) {
			return new Date(parseDate(date.toString()).getTime());
		}else if(date instanceof Date) {
			return (Date)date;
		}else if(date instanceof java.sql.Date || date instanceof java.sql.Timestamp) {
			return new Date(((Date)date).getTime());
		}
		throw new IllegalArgumentException("转换为java.util.Date时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
	
	
	/**
	 * 格式化为java.sql.Date类型的日期对象
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Object date){
		if(logger.isDebugEnabled()) {
			logger.debug("将实例[{}], 值为[{}] , 格式化为java.sql.Date类型的日期对象", date.getClass(), date);
		}
		if(date instanceof String) {
			return new java.sql.Date(parseDate(date.toString()).getTime());
		}else if(date instanceof Date || date instanceof java.sql.Timestamp) {
			return new java.sql.Date(((Date)date).getTime());
		}else if(date instanceof java.sql.Date) {
			return (java.sql.Date)date;
		}
		throw new IllegalArgumentException("要转换为java.sql.Date时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
	
	/**
	 * 格式化为java.sql.Timestamp类型的日期对象
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Timestamp parseSqlTimestamp(Object date) {
		if(logger.isDebugEnabled()) {
			logger.debug("将实例[{}], 值为[{}] , 格式化为java.sql.Timestamp类型的日期对象", date.getClass(), date);
		}
		if(date instanceof String) {
			return new java.sql.Timestamp(parseDate(date.toString()).getTime());
		}else if(date instanceof Date || date instanceof java.sql.Date) {
			return new java.sql.Timestamp(((Date)date).getTime());
		}else if(date instanceof java.sql.Timestamp) {
			return (java.sql.Timestamp)date;
		}
		throw new IllegalArgumentException("要转换为java.sql.Timestamp时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
}
