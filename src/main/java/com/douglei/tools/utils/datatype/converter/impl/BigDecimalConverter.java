package com.douglei.tools.utils.datatype.converter.impl;

import java.math.BigDecimal;

import com.douglei.tools.utils.datatype.converter.Converter;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;

/**
 * 
 * @author DougLei
 */
public class BigDecimalConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {BigDecimal.class};
	}

	@Override
	public BigDecimal convert(Object value) throws DataTypeConvertException {
		try {
			return new BigDecimal(value.toString());
		} catch (Exception e) {
			throw new DataTypeConvertException(value, BigDecimal.class, e);
		}
	}
}
