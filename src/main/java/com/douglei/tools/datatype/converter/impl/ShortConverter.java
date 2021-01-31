package com.douglei.tools.datatype.converter.impl;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class ShortConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Short.class, short.class};
	}

	@Override
	public Short convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		if(valueClazz == Short.class && targetClazz == short.class)
			return (Short) value;
		return Short.parseShort(value.toString());
	}
}
