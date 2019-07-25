package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class StringConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {String.class};
	}

	@Override
	public String doConvert(Object object) throws DataTypeConvertException {
		return object.toString();
	}
}
