package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class StringConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {String.class};
	}

	@Override
	public String convert(Object value) throws DataTypeConvertException {
		return value.toString();
	}
}
