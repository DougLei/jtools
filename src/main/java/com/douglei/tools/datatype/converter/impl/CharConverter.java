package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class CharConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Character.class, char.class};
	}

	@Override
	public Character convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Character.class && targetClazz == char.class)
			return (Character)value;
		return value.toString().charAt(0);
	}
}
