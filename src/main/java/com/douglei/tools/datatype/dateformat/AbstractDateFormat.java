package com.douglei.tools.datatype.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractDateFormat {
	private SimpleDateFormat sdf;
	
	public AbstractDateFormat() {
		this.sdf = new SimpleDateFormat(pattern());
	}
	
	/**
	 * 获取日期格式化的模式
	 * @return
	 */
	public abstract String pattern();
	
	/**
	 * 判断是否是日期类型
	 * @param str
	 * @return
	 */
	public abstract boolean isDate(String str);
	
	/**
	 * 将日期字符串转换为 {@link Date} 实例
	 * @param str
	 * @return
	 */
	public Date parse(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("将["+str+"]转换为[java.util.Date]实例时出现异常", e);
		}
	}
	
	/**
	 * 将 {@link Date} 实例转换为指定格式的字符串
	 * @param date
	 * @return
	 */
	public String format(Date date) {
		return sdf.format(date);
	}
	
//	/**
//	 * 获取格式化实例
//	 * @return
//	 */
//	protected abstract SimpleDateFormat sdf();
	
//	/**
//	 * 处理dateString, 获取最终需要的
//	 * @param dateString
//	 * @return
//	 */
//	protected String processDateString(String dateString) {
//		return dateString;
//	}
	
//	/**
//	 * 进行转化操作
//	 * @param date
//	 * @return
//	 */
//	final String format(Date date) {
//		return sdf().format(date);
//	}
}
