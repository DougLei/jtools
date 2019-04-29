package com.douglei.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.utils.datatype.ValidationUtil;

/**
 * 日期工具类
 * @author DougLei
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * -格式，简单日期格式化类
	 * <p>日期格式为：yyyy-MM-dd</p>
	 */
	private transient static final SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * -格式，详细日期格式化类
	 * <p>日期格式为：yyyy-MM-dd HH:mm:ss</p>
	 */
	private transient static final SimpleDateFormat sdfDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 带时区的日期格式化类
	 * <p>UTC通用标准时，以Z来标识</p>
	 */
	private transient static final SimpleDateFormat sdfTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString){
		logger.debug("将字符串[{}] 格式化为日期对象", dateString);
		if(!ValidationUtil.isDate(dateString)){
			logger.debug("日期字符串 [{}] 的格式错误", dateString);
			throw new IllegalArgumentException("日期字符串 ["+dateString+"] 的格式错误");
		}
		try {
			if(dateString.endsWith("Z")){
				return sdfTimeZone.parse(dateString.replace("Z", " UTC"));
			}else if(dateString.indexOf(":") != -1){
				return sdfDetail.parse(dateString);
			}else{
				return sdfSimple.parse(dateString);
			}
		} catch (ParseException e) {
			logger.error("格式化日期字符串 [{}] 为日期对象时, 出现异常:{}", dateString, ExceptionUtil.getExceptionDetailMessage(e));
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 格式化为sql类型的日期对象
	 * <p>没有时分秒</p>
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Object date){
		if(logger.isDebugEnabled()) {
			logger.debug("将实例[{}], 值为[{}] , 格式化为sql类型的日期对象, 没有时分秒", date.getClass(), date);
		}
		if(date instanceof String) {
			return new java.sql.Date(parseDate(date.toString()).getTime());
		}
		return new java.sql.Date(((Date)date).getTime());
	}
	
	/**
	 * 格式化为sql类型的日期对象
	 * <p>有时分秒</p>
	 * @param dateStr
	 * @return
	 */
	public static Timestamp parseSqlTimestamp(Object date) {
		if(logger.isDebugEnabled()) {
			logger.debug("将实例[{}], 值为[{}] , 格式化为sql类型的日期对象, 有时分秒", date.getClass(), date);
		}
		if(date instanceof String) {
			return new Timestamp(parseDate(date.toString()).getTime());
		}
		return new Timestamp(((Date)date).getTime());
	}
}
