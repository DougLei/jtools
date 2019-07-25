package com.douglei.tools.utils.datatype.converter;

/**
 * 转换异常
 * @author DougLei
 */
public class DataTypeConvertException extends RuntimeException{
	private static final long serialVersionUID = 5017042734765204635L;
	
	public DataTypeConvertException(String message) {
		super(message);
	}
	public DataTypeConvertException(Object value, Class<?> targetClass, Throwable e) {
		super("将数据["+value+"]转换为["+targetClass+"]类型时, 出现异常", e);
	}
	public DataTypeConvertException(String message, Throwable cause) {
		super(message, cause);
	}
}