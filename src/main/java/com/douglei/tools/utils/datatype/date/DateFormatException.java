package com.douglei.tools.utils.datatype.date;

import java.util.Date;

/**
 * 日期格式化异常
 * @author DougLei
 */
public class DateFormatException extends RuntimeException{
	private static final long serialVersionUID = -4937293295939853388L;

	public DateFormatException(String dateString, String pattern, Throwable e) {
		super("将字符串["+dateString+"]转换为["+pattern+"]格式的["+Date.class.getName()+"]实例时, 出现异常", e);
	}
}