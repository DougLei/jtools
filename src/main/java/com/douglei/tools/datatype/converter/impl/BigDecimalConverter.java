package com.douglei.tools.datatype.converter.impl;

import java.math.BigDecimal;

import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class BigDecimalConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {BigDecimal.class};
	}

	@Override
	public BigDecimal convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		return new BigDecimal(value.toString());
	}
}
