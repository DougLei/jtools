package com.douglei.tools.utils.datatype.converter;

/**
 * 不支持的数据类型转换异常
 * @author DougLei
 */
public class UnsupportDataTypeConvertException extends RuntimeException{
	private static final long serialVersionUID = 740081932537024815L;

	public UnsupportDataTypeConvertException(Object value, Class<?> targetClass) {
		super("目前不支持转换数据类型为["+targetClass.getName()+"]的数据["+value+"]");
	}
}
