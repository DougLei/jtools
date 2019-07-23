package com.douglei.tools.utils.datatype.dateformat;

/**
 * 不支持的DateFormat异常
 * @author DougLei
 */
public class UnsupportDateFormatException extends RuntimeException{
	private static final long serialVersionUID = -63908235411019026L;

	public UnsupportDateFormatException(String dateString) {
		super("目前不支持["+dateString+"]的日期格式");
	}
}
