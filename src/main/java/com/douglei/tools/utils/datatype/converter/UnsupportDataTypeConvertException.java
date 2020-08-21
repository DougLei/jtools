package com.douglei.tools.utils.datatype.converter;

/**
 * 
 * @author DougLei
 */
public class UnsupportDataTypeConvertException extends RuntimeException{
	private static final long serialVersionUID = -1570864109534680699L;

	public UnsupportDataTypeConvertException(Object value, Class<?> targetClass) {
		super("目前不支持转换数据类型为["+targetClass.getName()+"]的数据["+value+"]");
	}

	public UnsupportDataTypeConvertException(Object value) {
		super("数据转换容器目前不支持["+value.getClass().getName()+"]类型的数据");
	}
}
