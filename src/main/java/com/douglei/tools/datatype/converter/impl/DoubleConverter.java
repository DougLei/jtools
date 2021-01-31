package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class DoubleConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Double.class, double.class};
	}

	@Override
	public Double convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Double.class && targetClazz == double.class)
			return (Double)value;
		return Double.parseDouble(value.toString());
	}
}
