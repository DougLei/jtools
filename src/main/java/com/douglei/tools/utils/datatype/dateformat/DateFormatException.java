package com.douglei.tools.utils.datatype.dateformat;

import java.util.Date;

/**
 * 日期格式化异常
 * @author DougLei
 */
public class DateFormatException extends RuntimeException{
	private static final long serialVersionUID = 1100633618270559434L;

	public DateFormatException(String dateString, String pattern, Throwable e) {
		super("将字符串["+dateString+"]转换为["+pattern+"]格式的["+Date.class.getName()+"]实例时, 出现异常", e);
	}

	public DateFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}