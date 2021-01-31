package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class IntConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Integer.class, int.class};
	}

	@Override
	public Integer convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Integer.class && targetClazz == int.class)
			return (Integer) value;
		return Integer.parseInt(value.toString());
	}
}
