package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class FloatConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Float.class, float.class};
	}

	@Override
	public Float convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Float.class && targetClazz == float.class)
			return (Float) value;
		return Float.parseFloat(value.toString());
	}
}
