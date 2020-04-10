package com.douglei.tools.utils.datatype.dateformat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author DougLei
 */
public abstract class DateFormat {
	
	/**
	 * 判断object是否是日期类型
	 * @param dateString
	 * @return
	 */
	static final boolean isDate(Object obj) {
		return obj instanceof Date || obj instanceof java.sql.Date || obj instanceof Timestamp;
	}
	
	/**
	 * 根据参数判断, 当前格式化对象是否匹配, 可以对dateString进行格式化
	 * @param dateString
	 * @return
	 */
	protected abstract boolean match(String dateString);
	
	/**
	 * 获取格式化模式的字符串
	 * @return
	 */
	protected abstract String formatPattern();
	
	/**
	 * 获取格式化实例
	 * @return
	 */
	protected abstract SimpleDateFormat sdf();
	
	/**
	 * 处理dateString, 获取最终需要的
	 * @param dateString
	 * @return
	 */
	protected String processDateString(String dateString) {
		return dateString;
	}
	
	/**
	 * 进行转化操作
	 * @param dateString
	 * @return
	 */
	final Date parse(String dateString) {
		try {
			return sdf().parse(dateString);
		} catch (ParseException e) {
			throw new DateFormatException(dateString, formatPattern(), e);
		}
	}
	
	/**
	 * 进行转化操作
	 * @param date
	 * @return
	 */
	final String format(Date date) {
		return sdf().format(date);
	}
}
