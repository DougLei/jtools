package com.douglei.tools.utils.datatype.converter;

/**
 * 转换异常
 * @author DougLei
 */
public class ConvertException extends RuntimeException{
	private static final long serialVersionUID = 7146545504802524615L;

	public ConvertException(Object value, Class<?> targetClass, Throwable e) {
		super("将数据["+value+"]转换为["+targetClass.getName()+"]类型时, 出现异常", e);
	}
}
