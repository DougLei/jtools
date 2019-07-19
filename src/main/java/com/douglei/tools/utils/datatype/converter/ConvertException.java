package com.douglei.tools.utils.datatype.converter;

/**
 * 转换异常
 * @author DougLei
 */
public class ConvertException extends RuntimeException{
	private static final long serialVersionUID = 5009680436746510404L;
	
	public ConvertException(String message) {
		super(message);
	}
	public ConvertException(Object value, Class<?> targetClass, Throwable e) {
		super("将数据["+value+"]转换为["+targetClass+"]类型时, 出现异常", e);
	}
}