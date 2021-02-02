package com.douglei.tools.datatype;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douglei.tools.UtilRuntimeException;
import com.douglei.tools.datatype.dateformat.AbstractDateFormat;
import com.douglei.tools.file.scanner.impl.ClassScanner;

/**
 * 日期格式化工具类
 * @author DougLei
 */
public class DateFormatUtil {
	static final List<AbstractDateFormat> DATE_FORMATS = new ArrayList<AbstractDateFormat>(4);
	static {
		try {
			for (String clz : new ClassScanner().scan(AbstractDateFormat.class.getPackage().getName() + ".impl")) 
				DATE_FORMATS.add((AbstractDateFormat)Class.forName(clz).newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将参数转换为 {@link Date} 实例
	 * @param obj
	 * @return 
	 */
	public static Date parseDate(Object obj) {
		if(obj instanceof String) {
			for(AbstractDateFormat df : DATE_FORMATS) {
				if(df.isDate(obj.toString()))
					return df.parse(obj.toString());
			}
		}else if(obj instanceof Date) {
			return (Date)obj;
		}else if(obj instanceof Long || obj.getClass() == long.class) {
			return new Date((long)obj);
		}
		throw new UtilRuntimeException("目前不支持将[class="+obj.getClass().getName()+", value="+obj+"]转换为[java.util.Date]类型");
	}
	
	
	/**
	 * 将参数转换为 {@link java.sql.Date} 实例
	 * @param obj
	 * @return 
	 */
	public static java.sql.Date parseSqlDate(Object obj) {
		if(obj instanceof String) 
			return new java.sql.Date(parseDate(obj).getTime());
		if(obj instanceof java.sql.Date) 
			return (java.sql.Date)obj;
		if(obj instanceof Date) 
			return new java.sql.Date(((Date)obj).getTime());
		if(obj instanceof Long || obj.getClass() == long.class) 
			return new java.sql.Date((long)obj);
		throw new UtilRuntimeException("目前不支持将[class="+obj.getClass().getName()+", value="+obj+"]转换为[java.sql.Date]类型");
	}
	
	/**
	 * 将参数转换为 {@link Time} 实例
	 * @param obj
	 * @return 
	 */
	public static Time parseSqlTime(Object obj) {
		if(obj instanceof String) 
			return new Time(parseDate(obj).getTime());
		if(obj instanceof Time) 
			return (Time)obj;
		if(obj instanceof Date) 
			return new Time(((Date)obj).getTime());
		if(obj instanceof Long || obj.getClass() == long.class) 
			return new Time((long)obj);
		throw new UtilRuntimeException("目前不支持将[class="+obj.getClass().getName()+", value="+obj+"]转换为[java.sql.Time]类型");
	}
	
	/**
	 * 将参数转换为 {@link Timestamp} 实例
	 * @param obj
	 * @return 
	 */
	public static Timestamp parseSqlTimestamp(Object obj) {
		if(obj instanceof String) 
			return new Timestamp(parseDate(obj).getTime());
		if(obj instanceof Timestamp) 
			return (Timestamp)obj;
		if(obj instanceof Date) 
			return new Timestamp(((Date)obj).getTime());
		if(obj instanceof Long || obj.getClass() == long.class) 
			return new Timestamp((long)obj);
		throw new UtilRuntimeException("目前不支持将[class="+obj.getClass().getName()+", value="+obj+"]转换为[java.sql.Timestamp]类型");
	}
	
	/**
	 * 将 {@link Date} 实例转换为指定格式的字符串
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String format(String pattern, Date date) {
		for(AbstractDateFormat df : DATE_FORMATS) {
			if(df.pattern().equals(pattern))
				return df.format(date);
		}
		throw new UtilRuntimeException("目前不支持将[java.util.Date]实例转换为["+pattern+"]格式的字符串");
	}
	
	/**
	 * 获取指定格式的 {@link AbstractDateFormat} 实例
	 * @param pattern
	 * @return
	 */
	public static AbstractDateFormat getFormat(String pattern) {
		for(AbstractDateFormat df : DATE_FORMATS) {
			if(df.pattern().equals(pattern))
				return df;
		}
		throw new UtilRuntimeException("目前不存在["+pattern+"]格式的[com.douglei.tools.datatype.dateformat.AbstractDateFormat]实例");
	}
}
