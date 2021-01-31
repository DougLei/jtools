package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class BooleanConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Boolean.class, boolean.class};
	}

	@Override
	public Boolean convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Boolean.class && targetClazz == boolean.class)
			return (Boolean)value;
		return "true".equalsIgnoreCase(value.toString());
	}
}
