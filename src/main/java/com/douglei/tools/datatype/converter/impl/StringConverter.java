package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class StringConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {String.class};
	}

	@Override
	public String convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		return value.toString();
	}
}
