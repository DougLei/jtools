package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class ByteConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Byte.class, byte.class};
	}

	@Override
	public Byte convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Byte.class && targetClazz == byte.class)
			return (Byte)value;
		return Byte.parseByte(value.toString());
	}
}
