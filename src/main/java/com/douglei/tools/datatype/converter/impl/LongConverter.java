package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class LongConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Long.class, long.class};
	}

	@Override
	public Long convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Long.class && targetClazz == long.class)
			return (Long) value;
		return Long.parseLong(value.toString());
	}
}
